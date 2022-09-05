package cc.roadmaps.core.service.integrations.jpa.domain.repositories;

import cc.roadmaps.core.domain.model.courseAffiliation.CourseAffiliation;
import cc.roadmaps.core.domain.model.courseAffiliation.CourseAffiliationRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RepositoryDefinition(domainClass = CourseAffiliation.class, idClass = UUID.class)
public interface JpaCourseAffiliationRepository extends CourseAffiliationRepository {

    @Override
    @Transactional
    @Query("SELECT ca FROM CourseAffiliation ca " +
            "RIGHT JOIN Module m ON m.courseId = ca.courseId " +
            "RIGHT JOIN Leaf l ON l.moduleId = m.id " +
            "WHERE ca.userId = :userId AND l.id = :leafId")
    Optional<CourseAffiliation> getCourseAffiliationByLeafId(@Param("userId") UUID userId, @Param("leafId") UUID leafId);

    @Override
    @Transactional
    @Query("SELECT ca FROM CourseAffiliation ca " +
            "LEFT JOIN Module m ON m.courseId = ca.courseId " +
            "WHERE ca.userId = :userId AND m.id = :moduleId")
    Optional<CourseAffiliation> getCourseAffiliationByModuleId(@Param("userId") UUID userId, @Param("moduleId") UUID moduleId);

    @Override
    @Transactional
    @Query("SELECT ca FROM CourseAffiliation ca WHERE ca.userId = :userId AND ca.courseId = :courseId")
    Optional<CourseAffiliation> getCourseAffiliationByCourseId(@Param("userId") UUID userId, @Param("courseId") UUID courseId);

    @Override
    @Transactional
    @Modifying
    @Query("DELETE FROM CourseAffiliation ca WHERE ca.courseId = :courseId")
    void deleteAllByCourseId(@Param("courseId") UUID courseId);
}
