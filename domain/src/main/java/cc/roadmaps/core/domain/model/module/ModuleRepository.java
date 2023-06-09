package cc.roadmaps.core.domain.model.module;

import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository {

    Optional<Module> findModule(UUID id);

    Optional<Module> findModuleByLeafId(UUID leafId);

    void deleteAllByCourseId(UUID courseId);

    void save(Module module);
}
