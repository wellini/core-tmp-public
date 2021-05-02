package io.roadmaps.core.repository;

import io.roadmaps.core.model.entity.LinkLeaf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LinkLeafRepository extends JpaRepository<LinkLeaf, UUID> {
}
