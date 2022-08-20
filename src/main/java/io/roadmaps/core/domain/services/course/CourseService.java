package io.roadmaps.core.domain.services.course;

import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.user.User;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    Course getCourse(Long id);

    List<Course> getAllCourses();

    List<Course> getAllCoursesByAffiliationType(CourseAffiliationType affiliationType);

    List<Module> getModulesInCourse(Long id);

    List<User> getStudentsInCourse(Long id);
}
