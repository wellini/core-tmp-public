package io.roadmaps.core.rest.users.converters;

import io.roadmaps.core.model.entity.User;
import io.roadmaps.core.rest.users.dto.templates.UserResponseDtoTemplate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoConverter {

    private final ModelMapper modelMapper;

    public <T extends UserResponseDtoTemplate> T fromDomain(User course, Class<T> clazz) {
        return modelMapper.map(course, clazz);
    }
}
