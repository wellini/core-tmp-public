package io.roadmaps.core.domain.services.course;

import io.roadmaps.core.domain.exceptions.CreateCourseException;
import io.roadmaps.core.domain.exceptions.EnrollInCourseException;
import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.course.CourseRepository;
import io.roadmaps.core.domain.model.course.commands.CourseCreationCommand;
import io.roadmaps.core.domain.model.course.commands.EnrollInCourseCommandImpl;
import io.roadmaps.core.domain.model.course.commands.ModuleCreationCommand;
import io.roadmaps.core.domain.model.courseAffiliation.CourseAffiliationRepository;
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.domain.model.leaf.LeafRepository;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.module.ModuleRepository;
import io.roadmaps.core.domain.model.user.User;
import io.roadmaps.core.domain.services.CurrentUserIdProvider;
import io.roadmaps.core.domain.services.course.commands.EditPresentationServiceCommand;
import io.roadmaps.core.domain.services.course.implementations.EditPresentationCommandWithoutCoverUrl;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final UserService userService;
    private final CurrentUserIdProvider currentUserIdProvider;
    private final CourseAffiliationRepository courseAffiliationRepository;
    private final ModuleRepository moduleRepository;
    private final LeafRepository leafRepository;

    @Override
    @Transactional
    public Course getCourse(UUID id) {
        return repository.findCourse(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public List<Course> getAllCourses() {
        return repository.findAllCourses();
    }

    @Override
    @Transactional
    public List<Course> getAllCoursesByAffiliationType(CourseAffiliationType affiliationType) {
        Optional<User> currentUser = userService.getCurrentUser();
        if(currentUser.isPresent()) {
            return repository.findAllCoursesByAffiliationType(currentUser.get().getId(), affiliationType);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Module> getModulesInCourse(UUID id) {
        Course course = getCourse(id);
        return course.getModules();
    }

    @Override
    public UUID createCourse(CourseCreationCommand creationCommand) {
        log.debug("Create course");
        User author = userService.getCurrentUser()
                .orElseThrow(() -> new CreateCourseException("Current user not provided"));
        Course course = Course.create(author, creationCommand);
        repository.save(course);
        return course.getId();
    }

    @Override
    public UUID editPresentation(UUID id, EditPresentationServiceCommand serviceCommand) {
        log.debug("Edit presentation data in course with id {{}}", id);
        Course course = repository.findCourse(id).orElseThrow(EntityNotFoundException::new);
        EditPresentationCommandWithoutCoverUrl courseCommand = EditPresentationCommandWithoutCoverUrl.create(serviceCommand.getTitle(), serviceCommand.getCoverTheme());
        course.editPresentation(courseCommand);
        repository.save(course);
        return course.getId();
    }

    @Override
    public UUID createModule(UUID id, ModuleCreationCommand moduleCreationCommand) {
        log.debug("Create module in course with id {{}}", id);
        Course course = repository.findCourse(id).orElseThrow(EntityNotFoundException::new);
        UUID moduleId = course.addModule(moduleCreationCommand);
        repository.save(course);
        return moduleId;
    }

    @Override
    public UUID enrollInCourse(UUID id) {
        log.debug("Enroll in course with id {{}}", id);
        UUID currentUserId = currentUserIdProvider.getCurrentUserId().orElseThrow(() -> new EnrollInCourseException("No user id provided"));
        Course course = repository.findCourse(id).orElseThrow(EntityNotFoundException::new);
        course.enrollInCourse(new EnrollInCourseCommandImpl(currentUserId));
        repository.save(course);
        return course.getId();
    }

    @Override
    public UUID removeModule(UUID courseId, UUID moduleId) {
        log.debug("Remove module with id {{}} from course with id {{}}", moduleId, courseId);
        Course course = repository.findCourse(courseId).orElseThrow(EntityNotFoundException::new);
        course.removeModule(moduleId);
        repository.save(course);
        return course.getId();
    }

    @Override
    @Transactional
    public void removeCourse(UUID id) {
        log.debug("Remove course with id {{}}", id);
        Course course = getCourse(id);
        course.getModules().forEach(module -> leafRepository.deleteAllByModuleId(module.getId()));
        moduleRepository.deleteAllByCourseId(course.getId());
        courseAffiliationRepository.deleteAllByCourseId(course.getId());
        repository.delete(id);
    }
}
