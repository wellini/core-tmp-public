package io.roadmaps.core.integrations.jpa.repositories;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.leaf.LeafRepository;
import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryDefinition(domainClass = Leaf.class, idClass = UUID.class)
public interface JpaLeafRepository extends LeafRepository {

    @Override
    @Query("SELECT l FROM Leaf l WHERE l.id = :id")
    @Transactional
    Optional<Leaf> findLeaf(@Param("id") UUID id);

    @Override
    @Query("SELECT l FROM Leaf l WHERE l.id = :id AND l.type = :type")
    @Transactional
    Optional<Leaf> findLeafByIdAndType(@Param("id") UUID id, @Param("type") LeafType leafType);

    @Override
    @Transactional
    void save(Leaf leaf);
}
