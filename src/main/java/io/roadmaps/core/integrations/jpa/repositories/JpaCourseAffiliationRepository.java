package io.roadmaps.core.integrations.jpa.repositories;

import io.roadmaps.core.domain.model.courseAffiliation.CourseAffiliation;
import io.roadmaps.core.domain.model.courseAffiliation.CourseAffiliationRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RepositoryDefinition(domainClass = CourseAffiliation.class, idClass = Long.class)
public interface JpaCourseAffiliationRepository extends CourseAffiliationRepository {

    @Override
    @Transactional
    @Query("SELECT ca FROM CourseAffiliation ca " +
            "RIGHT JOIN Module m ON m.courseId = ca.courseId " +
            "RIGHT JOIN Leaf l ON l.moduleId = m.id " +
            "WHERE ca.userId = :userId AND l.id = :leafId")
    Optional<CourseAffiliation> getCourseAffiliationByLeafId(@Param("userId") Long userId, @Param("leafId") Long leafId);

    @Override
    @Transactional
    @Query("SELECT ca FROM CourseAffiliation ca " +
            "LEFT JOIN Module m ON m.courseId = ca.courseId " +
            "WHERE ca.userId = :userId AND m.id = :moduleId")
    Optional<CourseAffiliation> getCourseAffiliationByModuleId(@Param("userId") Long userId, @Param("moduleId") Long moduleId);

    @Override
    @Transactional
    @Query("SELECT ca FROM CourseAffiliation ca WHERE ca.userId = :userId AND ca.courseId = :courseId")
    Optional<CourseAffiliation> getCourseAffiliationByCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);

    @Override
    @Transactional
    @Modifying
    @Query("DELETE FROM CourseAffiliation ca WHERE ca.courseId = :courseId")
    void deleteAllByCourseId(@Param("courseId") Long courseId);
}
