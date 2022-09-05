package cc.roadmaps.core.domain.services.leaf;

import cc.roadmaps.core.domain.model.leaf.Leaf;

import java.util.UUID;

public interface LeafService {

    Leaf getLeaf(UUID id);
}
