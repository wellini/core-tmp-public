package cc.roadmaps.core.domain.services.course;

import cc.roadmaps.core.domain.model.course.Course;
import cc.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import cc.roadmaps.core.domain.model.module.Module;
import cc.roadmaps.core.domain.model.user.User;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    Course getCourse(UUID id);

    List<Course> getAllCourses();

    List<Course> getAllCoursesByAffiliationType(CourseAffiliationType affiliationType);

    List<Module> getModulesInCourse(UUID id);

    List<User> getStudentsInCourse(UUID id);
}
