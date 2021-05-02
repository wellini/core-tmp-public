package io.roadmaps.core.service;

import io.roadmaps.core.model.entity.CourseAffiliation;
import io.roadmaps.core.model.entity.enums.CourseAffiliationType;
import io.roadmaps.core.repository.CourseAffiliationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseAffiliationService {

    private final CourseAffiliationRepository repository;

    public CourseAffiliationType getAffiliationType(UUID courseId, UUID userId) {
        return repository.findAffiliationByCourseIdAndUserId(courseId, userId)
                .map(CourseAffiliation::getType)
                .orElse(null);
    }

    @Transactional
    public CourseAffiliation createOrUpdateAffiliation(UUID courseId, UUID userId, CourseAffiliationType type) {
        UUID affiliationId = repository.findAffiliationByCourseIdAndUserId(courseId, userId)
                .map(CourseAffiliation::getId)
                .orElse(null);
        return repository.save(new CourseAffiliation(
                affiliationId,
                courseId,
                userId,
                type
        ));
    }

    public void deleteAllAffiliationsByCourseId(UUID courseId) {
        repository.deleteAllAffiliationsByCourseId(courseId);
    }
}
