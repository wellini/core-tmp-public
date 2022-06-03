package io.roadmaps.core.domain.model.course;

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository {

    Optional<Course> findCourse(UUID id);

    List<Course> findAllCourses();

    List<Course> findAllCoursesByAffiliationType(UUID userId, CourseAffiliationType affiliationType);

    void delete(UUID id);

    void save(Course course);
}
