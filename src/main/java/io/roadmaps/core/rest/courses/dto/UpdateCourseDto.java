package io.roadmaps.core.rest.courses.dto;

import io.roadmaps.core.model.entity.enums.CourseCoverTheme;
import lombok.Data;

@Data
public class UpdateCourseDto {

    private String title;

    private CourseCoverTheme coverTheme;
}
