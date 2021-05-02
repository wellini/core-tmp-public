package io.roadmaps.core.rest.leaves.converter;

import io.roadmaps.core.model.entity.Leaf;
import io.roadmaps.core.model.entity.LinkLeaf;
import io.roadmaps.core.model.entity.TextLeaf;
import io.roadmaps.core.rest.leaves.dto.CreateLeafDto;
import io.roadmaps.core.rest.leaves.dto.UpdateLeafDto;
import io.roadmaps.core.rest.leaves.dto.templates.LeafResponseDtoTemplate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeafDtoConverter {

    private final ModelMapper modelMapper;

    public <T extends LeafResponseDtoTemplate> T fromDomain(Leaf leaf, Class<T> clazz) {
        return modelMapper.map(leaf, clazz);
    }

    public Leaf toDomain(CreateLeafDto dto) {
        switch (dto.getType()) {
            case TEXT:
                return modelMapper.map(dto, TextLeaf.class);
            case LINK:
                return modelMapper.map(dto, LinkLeaf.class);
            default:
                return null;
        }
    }

    public <T extends Leaf> void update(T leaf, UpdateLeafDto dto) {
        modelMapper.map(dto, leaf);
    }
}
