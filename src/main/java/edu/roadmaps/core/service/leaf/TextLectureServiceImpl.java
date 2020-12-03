package edu.roadmaps.core.service.leaf;

import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.model.entity.leaf.TextLectureLeaf;
import edu.roadmaps.core.repository.LeafBaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextLectureServiceImpl extends LeafBaseServiceImpl<TextLectureLeaf> {
    @Autowired
    public TextLectureServiceImpl(LeafBaseRepository<TextLectureLeaf> repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public LeafType getLeafType() {
        return LeafType.TEXT_LECTURE;
    }
}
