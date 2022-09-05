package cc.roadmaps.core.domain.services.courseAffiliation;

import cc.roadmaps.core.domain.model.courseAffiliation.CourseAffiliation;
import cc.roadmaps.core.domain.model.courseAffiliation.CourseAffiliationRepository;
import cc.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class CourseAffiliationServiceImpl implements CourseAffiliationService {

    private final CourseAffiliationRepository repository;

    @Override
    public CourseAffiliationType safeGetAffiliationTypeByLeafId(UUID userId, UUID leafId) {
        Optional<CourseAffiliation> courseAffiliation = repository.getCourseAffiliationByLeafId(userId, leafId);
        return courseAffiliation.map(CourseAffiliation::getType).orElse(CourseAffiliationType.GUEST);
    }

    @Override
    public CourseAffiliationType safeGetAffiliationTypeByModuleId(UUID userId, UUID moduleId) {
        Optional<CourseAffiliation> courseAffiliation = repository.getCourseAffiliationByModuleId(userId, moduleId);
        return courseAffiliation.map(CourseAffiliation::getType).orElse(CourseAffiliationType.GUEST);
    }

    @Override
    public CourseAffiliationType safeGetAffiliationTypeByCourseId(UUID userId, UUID courseId) {
        Optional<CourseAffiliation> courseAffiliation = repository.getCourseAffiliationByCourseId(userId, courseId);
        return courseAffiliation.map(CourseAffiliation::getType).orElse(CourseAffiliationType.GUEST);
    }

    @Override
    public CourseAffiliationType safeResolveAffiliation(UUID userId, AffiliationResolvable resolvable) {
        if(Objects.nonNull(resolvable.getCourseId())) {
            return safeGetAffiliationTypeByCourseId(userId, resolvable.getCourseId());
        } else if (Objects.nonNull(resolvable.getModuleId())) {
            return safeGetAffiliationTypeByModuleId(userId, resolvable.getModuleId());
        } else if (Objects.nonNull(resolvable.getLeafId())) {
            return safeGetAffiliationTypeByLeafId(userId, resolvable.getLeafId());
        } else {
            throw new IllegalArgumentException("Identifier not set");
        }
    }
}
