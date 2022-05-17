package io.roadmaps.core.integrations.jpa.converters;

import io.roadmaps.core.domain.model.leaf.enums.LeafType;

import javax.persistence.AttributeConverter;

public class LeafTypeConverter implements AttributeConverter<LeafType, String> {

    @Override
    public String convertToDatabaseColumn(LeafType attribute) {
        return attribute.name();
    }

    @Override
    public LeafType convertToEntityAttribute(String dbData) {
        return LeafType.valueOf(dbData);
    }
}
