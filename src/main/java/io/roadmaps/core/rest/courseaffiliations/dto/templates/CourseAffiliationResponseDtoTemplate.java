package io.roadmaps.core.rest.courseaffiliations.dto.templates;

import io.roadmaps.core.model.entity.enums.CourseAffiliationType;
import lombok.Data;

import java.util.UUID;

@Data
public abstract class CourseAffiliationResponseDtoTemplate {

    private UUID id;

    private UUID courseId;

    private UUID userId;

    private CourseAffiliationType type;
}
