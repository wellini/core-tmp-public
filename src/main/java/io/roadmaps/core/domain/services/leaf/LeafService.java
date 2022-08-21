package io.roadmaps.core.domain.services.leaf;

import io.roadmaps.core.domain.model.leaf.Leaf;

import java.util.UUID;

public interface LeafService {

    Leaf getLeaf(UUID id);
}
