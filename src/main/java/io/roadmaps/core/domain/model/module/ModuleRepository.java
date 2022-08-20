package io.roadmaps.core.domain.model.module;

import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository {

    Optional<Module> findModule(Long id);

    Optional<Module> findModuleByLeafId(Long leafId);

    void deleteAllByCourseId(Long courseId);

    void save(Module module);
}
