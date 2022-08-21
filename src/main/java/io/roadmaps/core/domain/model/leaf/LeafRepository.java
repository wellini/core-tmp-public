package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.model.leaf.enums.LeafType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeafRepository {

    Optional<Leaf> findLeaf(UUID id);

    Optional<Leaf> findLeafByIdAndType(UUID id, LeafType leafType);

    void deleteAllByCourseId(UUID courseId);

    void save(Leaf leaf);
}
