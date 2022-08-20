package io.roadmaps.core.domain.model.courseAffiliation.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum CourseAffiliationType {
    STUDENT, TEACHER, GUEST
}
