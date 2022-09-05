package cc.roadmaps.core.domain.services.courseAffiliation;

import java.util.UUID;

public interface AffiliationResolvable {

    default UUID getCourseId() {
        return null;
    }

    default UUID getModuleId() {
        return null;
    }

    default UUID getLeafId() {
        return null;
    }
}
