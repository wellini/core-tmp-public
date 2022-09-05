package cc.roadmaps.core.domain.services.courseAffiliation;

import cc.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;

import java.util.UUID;

public interface CourseAffiliationService {

    CourseAffiliationType safeGetAffiliationTypeByLeafId(UUID userId, UUID leafId);

    CourseAffiliationType safeGetAffiliationTypeByModuleId(UUID userId, UUID moduleId);

    CourseAffiliationType safeGetAffiliationTypeByCourseId(UUID userId, UUID courseId);

    CourseAffiliationType safeResolveAffiliation(UUID userId, AffiliationResolvable resolvable);
}
