package io.roadmaps.core.rest.courses.converters;

import io.roadmaps.core.model.entity.Course;
import io.roadmaps.core.rest.courses.dto.CreateCourseDto;
import io.roadmaps.core.rest.courses.dto.UpdateCourseDto;
import io.roadmaps.core.rest.courses.dto.templates.CourseResponseDtoTemplate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseDtoConverter {

    private final ModelMapper modelMapper;

    public Course toDomain(CreateCourseDto dto) {
        return modelMapper.map(dto, Course.class);
    }

    public <T extends CourseResponseDtoTemplate> T fromDomain(Course course, Class<T> clazz) {
        return modelMapper.map(course, clazz);
    }

    public void update(Course module, UpdateCourseDto dto) {
        modelMapper.map(dto, module);
    }
}
