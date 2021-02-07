package edu.roadmaps.core.rest.leaves.converter;


import edu.roadmaps.core.model.entity.Leaf;
import edu.roadmaps.core.model.entity.LeafType;
import edu.roadmaps.core.model.entity.TextLectureLeaf;
import edu.roadmaps.core.rest.leaves.dto.LeafDto;
import edu.roadmaps.core.rest.leaves.dto.LectureFullDetailLeafDto;
import edu.roadmaps.core.rest.leaves.dto.TextLectureInDetailDto;
import edu.roadmaps.core.rest.leaves.dto.TextLectureInFullDetailDto;
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
    public LectureFullDetailLeafDto toFullDto(Leaf l) {
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
