package io.roadmaps.core.domain.services.courseAffiliation;

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;

import java.util.UUID;

public interface CourseAffiliationService {

    CourseAffiliationType safeGetAffiliationTypeByLeafId(Long userId, Long leafId);

    CourseAffiliationType safeGetAffiliationTypeByModuleId(Long userId, Long moduleId);

    CourseAffiliationType safeGetAffiliationTypeByCourseId(Long userId, Long courseId);

    CourseAffiliationType safeResolveAffiliation(Long userId, AffiliationResolvable resolvable);
}
