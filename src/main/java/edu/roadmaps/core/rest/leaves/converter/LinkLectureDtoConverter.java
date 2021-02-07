package edu.roadmaps.core.rest.leaves.converter;


import edu.roadmaps.core.model.entity.Leaf;
import edu.roadmaps.core.model.entity.LeafType;
import edu.roadmaps.core.model.entity.LinkLectureLeaf;
import edu.roadmaps.core.rest.leaves.dto.LeafDto;
import edu.roadmaps.core.rest.leaves.dto.LectureFullDetailLeafDto;
import edu.roadmaps.core.rest.leaves.dto.LinkLectureInDetailDto;
import edu.roadmaps.core.rest.leaves.dto.LinkLectureInFullDetailDto;
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
    public LectureFullDetailLeafDto toFullDto(Leaf l) {
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
