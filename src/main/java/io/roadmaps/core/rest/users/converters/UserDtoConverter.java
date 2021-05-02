package io.roadmaps.core.rest.users.converters;

import io.roadmaps.core.model.entity.User;
import io.roadmaps.core.rest.users.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoConverter {

    private final ModelMapper modelMapper;

    public UserResponseDto fromDomain(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }
}
