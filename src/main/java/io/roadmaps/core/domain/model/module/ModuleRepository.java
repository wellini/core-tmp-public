package io.roadmaps.core.domain.model.module;

import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository {

    Optional<Module> findModule(UUID id);

    void deleteAllByCourseId(UUID courseId);

    void save(Module module);
}
