package io.roadmaps.core.repository;

import io.roadmaps.core.model.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {

    List<Module> findAllByCourseId(UUID courseId);
}
