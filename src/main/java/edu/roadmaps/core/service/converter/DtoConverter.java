package edu.roadmaps.core.service.converter;

import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.rest.dto.leaf.LeafDto;

public interface DtoConverter {
    LeafDto toDetailDto(Leaf l);
    LeafDto toFullDto(Leaf l);
    Leaf toEntity(LeafDto dto);
    LeafType getLeafType();
}
