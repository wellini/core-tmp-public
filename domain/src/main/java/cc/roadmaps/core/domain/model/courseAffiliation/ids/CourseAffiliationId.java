package cc.roadmaps.core.domain.model.courseAffiliation.ids;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CourseAffiliationId implements Serializable {

    @EqualsAndHashCode.Include
    private UUID courseId;

    @EqualsAndHashCode.Include
    private UUID userId;
}
