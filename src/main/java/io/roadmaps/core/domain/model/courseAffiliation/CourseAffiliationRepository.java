package io.roadmaps.core.domain.model.courseAffiliation;

import java.util.UUID;

public interface CourseAffiliationRepository {

    void deleteAllByCourseId(UUID courseId);
}
