package io.roadmaps.core.domain.model.courseAffiliation;

import java.util.Optional;
import java.util.UUID;

public interface CourseAffiliationRepository {

    Optional<CourseAffiliation> getCourseAffiliationByLeafId(UUID userId, UUID leafId);

    Optional<CourseAffiliation> getCourseAffiliationByModuleId(UUID userId, UUID moduleId);

    Optional<CourseAffiliation> getCourseAffiliationByCourseId(UUID userId, UUID courseId);

    void deleteAllByCourseId(UUID courseId);
}
