package io.roadmaps.core.domain.model.courseAffiliation;

import java.util.Optional;
import java.util.UUID;

public interface CourseAffiliationRepository {

    Optional<CourseAffiliation> getCourseAffiliationByLeafId(Long userId, Long leafId);

    Optional<CourseAffiliation> getCourseAffiliationByModuleId(Long userId, Long moduleId);

    Optional<CourseAffiliation> getCourseAffiliationByCourseId(Long userId, Long courseId);

    void deleteAllByCourseId(Long courseId);
}
