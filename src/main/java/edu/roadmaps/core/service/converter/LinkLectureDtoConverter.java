package edu.roadmaps.core.service.converter;


import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.model.entity.leaf.LinkLectureLeaf;
import edu.roadmaps.core.rest.dto.leaf.LeafDto;
import edu.roadmaps.core.rest.leaves.lecture.dto.link.LinkLectureInDetailDto;
import edu.roadmaps.core.rest.leaves.lecture.dto.link.LinkLectureInFullDetailDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkLectureDtoConverter implements DtoConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public LinkLectureDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LeafDto toDetailDto(Leaf l) {
        return modelMapper.map(l, LinkLectureInDetailDto.class);
    }

    @Override
    public LeafDto toFullDto(Leaf l) {
        return modelMapper.map(l, LinkLectureInFullDetailDto.class);
    }

    @Override
    public Leaf toEntity(LeafDto dto) {
        return modelMapper.map(dto, LinkLectureLeaf.class);
    }

    @Override
    public LeafType getLeafType() {
        return LeafType.LINK_LECTURE;
    }
}
