package io.roadmaps.core.domain.model.course;

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository {

    Optional<Course> findCourse(Long id);

    Optional<Course> findCourseByModuleId(Long moduleId);

    Optional<Course> findCourseByLeafId(Long leafId);

    List<Course> findAllCourses();

    List<Course> findAllCoursesByAffiliationType(Long userId, CourseAffiliationType affiliationType);

    void delete(Long id);

    void save(Course course);
}
