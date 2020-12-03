package edu.roadmaps.core.repository;

import edu.roadmaps.core.model.entity.leaf.Leaf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface LeafBaseRepository<E extends Leaf> extends JpaRepository <E, UUID> {
    List<E> findByTitle(String title);
}
