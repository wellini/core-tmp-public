package io.roadmaps.core.domain.services.module;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.module.Module;

import java.util.List;

public interface ModuleService {

    Module getModule(Long id);

    List<Leaf> getLeavesInModule(Long id);
}
