package io.roadmaps.core.domain.services.course;

import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.course.CourseRepository;
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.user.User;
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

    @Override
    @Transactional
    public Course getCourse(Long id) {
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
        Optional<User> currentUser = userService.findCurrentUser();
        if(currentUser.isPresent()) {
            return repository.findAllCoursesByAffiliationType(currentUser.get().getId(), affiliationType);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Module> getModulesInCourse(Long id) {
        Course course = getCourse(id);
        return course.getModules();
    }

    @Override
    public List<User> getStudentsInCourse(Long id) {
        return userService.getStudentsInCourse(id);
    }
}
