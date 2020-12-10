package edu.roadmaps.core.service.leaf;


import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import java.util.UUID;

public interface LeafService {

    Leaf create(Leaf leaf);
    Leaf update(Leaf leaf, UUID uuid);
    void delete(UUID uuid);
    Leaf get(UUID uuid);

    LeafType getLeafType();
}
