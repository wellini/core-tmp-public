package io.roadmaps.core.rest.courseaffiliations.converters;

import io.roadmaps.core.model.entity.CourseAffiliation;
import io.roadmaps.core.rest.courseaffiliations.dto.templates.CourseAffiliationResponseDtoTemplate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseAffiliationDtoConverter {

    private final ModelMapper modelMapper;

    public <T extends CourseAffiliationResponseDtoTemplate> T fromDomain(CourseAffiliation course, Class<T> clazz) {
        return modelMapper.map(course, clazz);
    }
}
