package edu.roadmaps.core.repository;

import edu.roadmaps.core.model.entity.LinkLectureLeaf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LinkLectureRepository extends JpaRepository<LinkLectureLeaf, UUID> {
}
