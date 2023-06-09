package cc.roadmaps.core.service.integrations.jpa.extauth.converters;

import cc.roadmaps.extauth.model.enums.AuthProviderType;

import javax.persistence.AttributeConverter;

public class AuthProviderTypeConverter implements AttributeConverter<AuthProviderType, String> {

    @Override
    public String convertToDatabaseColumn(AuthProviderType attribute) {
        return attribute.name();
    }

    @Override
    public AuthProviderType convertToEntityAttribute(String dbData) {
        return AuthProviderType.valueOf(dbData);
    }
}
