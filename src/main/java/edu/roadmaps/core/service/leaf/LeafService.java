package edu.roadmaps.core.service.leaf;


import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import java.util.UUID;

public interface LeafService<E extends Leaf> {

    E create(E leaf);
    E update(E leaf, UUID uuid);
    void delete(UUID uuid);
    E get(UUID uuid);

    LeafType getLeafType();
}
