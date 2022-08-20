package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.model.leaf.enums.LeafType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeafRepository {

    Optional<Leaf> findLeaf(Long id);

    Optional<Leaf> findLeafByIdAndType(Long id, LeafType leafType);

    void deleteAllByCourseId(Long courseId);

    void save(Leaf leaf);
}
