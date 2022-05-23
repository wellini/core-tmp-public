package io.roadmaps.core.integrations.jpa.repositories;

import io.roadmaps.core.domain.model.courseAffiliation.CourseAffiliation;
import io.roadmaps.core.domain.model.courseAffiliation.CourseAffiliationRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.UUID;

@RepositoryDefinition(domainClass = CourseAffiliation.class, idClass = UUID.class)
public interface JpaCourseAffiliationRepository extends CourseAffiliationRepository {

    @Override
    @Transactional
    @Modifying
    @Query("DELETE FROM CourseAffiliation ca WHERE ca.courseId = :courseId")
    void deleteAllByCourseId(@Param("courseId") UUID courseId);
}
