package edu.roadmaps.core.service.leaf;

import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.repository.LeafBaseRepository;
import org.modelmapper.ModelMapper;

public class LeafServiceImpl extends LeafBaseServiceImpl<Leaf>{
    public LeafServiceImpl(LeafBaseRepository<Leaf> repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public LeafType getLeafType() {
        return LeafType.LEAF;
    }
}
