package io.roadmaps.core.model.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum CourseAffiliationType {
    STUDENT, TEACHER
}
