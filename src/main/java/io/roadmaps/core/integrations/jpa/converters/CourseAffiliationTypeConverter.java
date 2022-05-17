package io.roadmaps.core.integrations.jpa.converters;

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;

import javax.persistence.AttributeConverter;

public class CourseAffiliationTypeConverter implements AttributeConverter<CourseAffiliationType, String>  {

    @Override
    public String convertToDatabaseColumn(CourseAffiliationType attribute) {
        return attribute.name();
    }

    @Override
    public CourseAffiliationType convertToEntityAttribute(String dbData) {
        return CourseAffiliationType.valueOf(dbData);
    }
}
