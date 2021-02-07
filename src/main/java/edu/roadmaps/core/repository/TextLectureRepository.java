package edu.roadmaps.core.repository;

import edu.roadmaps.core.model.entity.TextLectureLeaf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface TextLectureRepository extends JpaRepository<TextLectureLeaf, UUID> {
}
