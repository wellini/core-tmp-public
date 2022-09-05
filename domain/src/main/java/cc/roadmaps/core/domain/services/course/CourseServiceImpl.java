package cc.roadmaps.core.domain.services.course;

import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.model.course.Course;
import cc.roadmaps.core.domain.model.course.CourseRepository;
import cc.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import cc.roadmaps.core.domain.model.module.Module;
import cc.roadmaps.core.domain.model.user.User;
import cc.roadmaps.core.domain.services.user.UserService;
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

    @Override
    @Transactional
    public Course getCourse(UUID id) {
        return repository.findCourse(id).orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Course.class, id));
    }

    @Override
    @Transactional
    public List<Course> getAllCourses() {
        return repository.findAllCourses();
    }

    @Override
    @Transactional
    public List<Course> getAllCoursesByAffiliationType(CourseAffiliationType affiliationType) {
        Optional<User> currentUser = userService.findCurrentUser();
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
    public List<User> getStudentsInCourse(UUID id) {
        return userService.getStudentsInCourse(id);
    }
}
