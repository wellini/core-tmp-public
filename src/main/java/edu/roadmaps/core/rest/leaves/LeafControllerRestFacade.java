package edu.roadmaps.core.rest.leaves;

import edu.roadmaps.core.model.entity.Leaf;
import edu.roadmaps.core.rest.leaves.converter.DtoConverter;
import edu.roadmaps.core.rest.leaves.dto.LeafInCreateDto;
import edu.roadmaps.core.rest.leaves.dto.LeafInUpdateDto;
import edu.roadmaps.core.rest.leaves.dto.LectureFullDetailLeafDto;
import edu.roadmaps.core.rest.leaves.converter.LeafDtoConverterStrategy;
import edu.roadmaps.core.service.leaf.LeafService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LeafControllerRestFacade {

    private final LeafDtoConverterStrategy dtoConverterStrategy;
    private final LeafService service;

    public LectureFullDetailLeafDto create(UUID moduleId, LeafInCreateDto dto) {
        DtoConverter converter = dtoConverterStrategy.getConverter(dto.getType());
        Leaf leaf = converter.toEntity(dto);
        return converter.toFullDto(service.create(leaf));
    }

    public LectureFullDetailLeafDto get(UUID id) {
        Leaf leaf = service.get(id);
        DtoConverter converter = dtoConverterStrategy.getConverter(leaf.getType());
        return converter.toFullDto(leaf);
    }

    public void delete(UUID id) {
        service.deleteById(id);
    }

    public LectureFullDetailLeafDto update(UUID id, LeafInUpdateDto dto) {
        DtoConverter converter = dtoConverterStrategy.getConverter(dto.getType());
        Leaf leaf = converter.toEntity(dto);
        leaf.setId(id);
        return converter.toFullDto(service.update(leaf));
    }
}
