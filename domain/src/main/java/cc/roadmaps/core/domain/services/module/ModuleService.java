package cc.roadmaps.core.domain.services.module;

import cc.roadmaps.core.domain.model.leaf.Leaf;
import cc.roadmaps.core.domain.model.module.Module;

import java.util.List;
import java.util.UUID;

public interface ModuleService {

    Module getModule(UUID id);

    List<Leaf> getLeavesInModule(UUID id);
}
