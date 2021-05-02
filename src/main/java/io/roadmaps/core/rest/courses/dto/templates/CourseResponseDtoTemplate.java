package io.roadmaps.core.rest.courses.dto.templates;

import io.roadmaps.core.model.entity.enums.CourseAffiliationType;
import io.roadmaps.core.model.entity.enums.CourseCoverTheme;
import lombok.Data;

import java.util.UUID;

@Data
public abstract class CourseResponseDtoTemplate {

    private UUID id;

    private String title;

    private UUID authorId;

    private CourseCoverTheme coverTheme;

    private String coverUrl;

    private CourseAffiliationType affiliationType;
}
