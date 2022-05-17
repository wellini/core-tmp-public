package io.roadmaps.core.domain.services.module;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.module.commands.EditModuleTitleCommand;
import io.roadmaps.core.domain.model.module.commands.LeafCreationCommand;

import java.util.List;
import java.util.UUID;

public interface ModuleService {

    Module getModule(UUID id);

    List<Leaf> getLeavesInModule(UUID id);

    UUID createLeaf(UUID id, LeafCreationCommand leafCreationCommand);

    UUID editTitle(UUID id, EditModuleTitleCommand editModuleTitleCommand);

    UUID removeLeaf(UUID moduleId, UUID leafId);
}
