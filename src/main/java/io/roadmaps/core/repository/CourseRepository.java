package io.roadmaps.core.repository;

import io.roadmaps.core.model.entity.Course;
import io.roadmaps.core.model.entity.enums.CourseAffiliationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    Page<Course> findCoursesByAuthorId(UUID authorId, Pageable pageable);

    @Query(value = "select c from Course c" +
            "    inner join CourseAffiliation ca" +
            "    on c.id = ca.courseId" +
            "    where ca.userId = ?1 and ca.type = ?2")
    Page<Course> findCoursesByUserIdAndByAffiliationType(UUID userId, CourseAffiliationType affiliationType, Pageable pageable);
}
