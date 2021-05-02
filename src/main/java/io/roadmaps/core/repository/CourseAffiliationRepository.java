package io.roadmaps.core.repository;

import io.roadmaps.core.model.entity.CourseAffiliation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseAffiliationRepository extends JpaRepository<CourseAffiliation, UUID> {

    Optional<CourseAffiliation> findAffiliationByCourseIdAndUserId(UUID courseId, UUID userId);

    void deleteAllAffiliationsByCourseId(UUID courseId);
}
