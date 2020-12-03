package edu.roadmaps.core.service.leaf;

import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.model.entity.leaf.LinkLectureLeaf;
import edu.roadmaps.core.repository.LeafBaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkLectureServiceImpl extends LeafBaseServiceImpl<LinkLectureLeaf> {
    @Autowired
    public LinkLectureServiceImpl(LeafBaseRepository<LinkLectureLeaf> repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public LeafType getLeafType() {
        return LeafType.LINK_LECTURE;
    }
}
