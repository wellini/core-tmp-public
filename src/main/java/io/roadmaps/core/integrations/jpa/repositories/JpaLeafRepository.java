package io.roadmaps.core.integrations.jpa.repositories;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.leaf.LeafRepository;
import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@RepositoryDefinition(domainClass = Leaf.class, idClass = Long.class)
public interface JpaLeafRepository extends LeafRepository {

    @Override
    @Query("SELECT l FROM Leaf l WHERE l.id = :id")
    @Transactional
    Optional<Leaf> findLeaf(@Param("id") Long id);

    @Override
    @Query("SELECT l FROM Leaf l WHERE l.id = :id AND l.type = :type")
    @Transactional
    Optional<Leaf> findLeafByIdAndType(@Param("id") Long id, @Param("type") LeafType leafType);

    @Override
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM leaves l USING modules m WHERE l.module_id = m.id AND m.course_id = :courseId", nativeQuery = true)
    void deleteAllByCourseId(@Param("courseId") Long courseId);

    @Override
    @Transactional
    void save(Leaf leaf);
}
