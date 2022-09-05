package cc.roadmaps.core.service.integrations.jpa.domain.converters;

import cc.roadmaps.core.domain.model.course.enums.CourseCoverTheme;

import javax.persistence.AttributeConverter;

public class CourseCoverThemeConverter implements AttributeConverter<CourseCoverTheme, String> {

    @Override
    public String convertToDatabaseColumn(CourseCoverTheme attribute) {
        return attribute.name();
    }

    @Override
    public CourseCoverTheme convertToEntityAttribute(String dbData) {
        return CourseCoverTheme.valueOf(dbData);
    }
}
