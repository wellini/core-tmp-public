package io.roadmaps.core.rest.courses.dto.response;

import io.roadmaps.core.rest.courses.dto.templates.CourseResponseDtoTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
public class GetCourseResponseDto extends CourseResponseDtoTemplate {
    @Getter
    @Setter
    private String authorFullname;
}
