package io.roadmaps.core.repository;

import io.roadmaps.core.model.entity.Leaf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LeafRepository extends JpaRepository<Leaf, UUID> {

    List<Leaf> findAllByCourseId(UUID courseId);
}
