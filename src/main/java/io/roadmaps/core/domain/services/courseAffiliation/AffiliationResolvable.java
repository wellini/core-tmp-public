package io.roadmaps.core.domain.services.courseAffiliation;

public interface AffiliationResolvable {

    default Long getCourseId() {
        return null;
    }

    default Long getModuleId() {
        return null;
    }

    default Long getLeafId() {
        return null;
    }
}
