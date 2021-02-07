package edu.roadmaps.core.rest.leaves.converter;

import edu.roadmaps.core.model.entity.Leaf;
import edu.roadmaps.core.model.entity.LeafType;
import edu.roadmaps.core.rest.leaves.dto.LeafDto;
import edu.roadmaps.core.rest.leaves.dto.LectureFullDetailLeafDto;

public interface DtoConverter {
    LeafDto toDetailDto(Leaf l);
    LectureFullDetailLeafDto toFullDto(Leaf l);
    Leaf toEntity(LeafDto dto);
    LeafType getLeafType();
}
