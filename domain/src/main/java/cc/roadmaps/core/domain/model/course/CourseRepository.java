package cc.roadmaps.core.domain.model.course;

import cc.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository {

    Optional<Course> findCourse(UUID id);

    Optional<Course> findCourseByModuleId(UUID moduleId);

    Optional<Course> findCourseByLeafId(UUID leafId);

    List<Course> findAllCourses();

    List<Course> findAllCoursesByAffiliationType(UUID userId, CourseAffiliationType affiliationType);

    void delete(UUID id);

    void save(Course course);
}
