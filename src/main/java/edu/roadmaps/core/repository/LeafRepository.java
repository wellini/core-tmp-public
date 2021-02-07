package edu.roadmaps.core.repository;

import edu.roadmaps.core.model.entity.Leaf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LeafRepository extends JpaRepository<Leaf, UUID> {
}
