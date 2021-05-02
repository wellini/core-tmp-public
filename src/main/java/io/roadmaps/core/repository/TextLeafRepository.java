package io.roadmaps.core.repository;

import io.roadmaps.core.model.entity.TextLeaf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TextLeafRepository extends JpaRepository<TextLeaf, UUID> {
}
