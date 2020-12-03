package edu.roadmaps.core.service.converter;


import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.model.entity.leaf.TextLectureLeaf;
import edu.roadmaps.core.rest.dto.leaf.LeafDto;
import edu.roadmaps.core.rest.leaves.lecture.dto.text.TextLectureInDetailDto;
import edu.roadmaps.core.rest.leaves.lecture.dto.text.TextLectureInFullDetailDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextLectureDtoConverter implements DtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public TextLectureDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LeafDto toDetailDto(Leaf l) {
        return modelMapper.map(l, TextLectureInDetailDto.class);
    }

    @Override
    public LeafDto toFullDto(Leaf l) {
        return modelMapper.map(l, TextLectureInFullDetailDto.class);
    }

    @Override
    public Leaf toEntity(LeafDto dto) {
        return modelMapper.map(dto, TextLectureLeaf.class);
    }

    @Override
    public LeafType getLeafType() {
        return LeafType.TEXT_LECTURE;
    }
}
