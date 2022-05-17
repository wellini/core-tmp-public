package io.roadmaps.core.domain.services.module;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.module.ModuleRepository;
import io.roadmaps.core.domain.model.module.commands.EditModuleTitleCommand;
import io.roadmaps.core.domain.model.module.commands.LeafCreationCommand;
import io.roadmaps.core.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository repository;

    @Override
    public Module getModule(UUID id) {
        return repository.findModule(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Leaf> getLeavesInModule(UUID id) {
        Module module = getModule(id);
        return module.getLeaves();
    }

    @Override
    public UUID createLeaf(UUID id, LeafCreationCommand leafCreationCommand) {
        log.debug("Create leaf in module with id {{}}", id);
        Module module = repository.findModule(id).orElseThrow(EntityNotFoundException::new);
        UUID leafId = module.addLeaf(leafCreationCommand);
        repository.save(module);
        return leafId;
    }

    @Override
    public UUID editTitle(UUID id, EditModuleTitleCommand editModuleTitleCommand) {
        log.debug("Edit title of module with id {{}}", id);
        Module module = repository.findModule(id).orElseThrow(EntityNotFoundException::new);
        module.editTitle(editModuleTitleCommand);
        repository.save(module);
        return module.getId();
    }

    @Override
    public UUID removeLeaf(UUID moduleId, UUID leafId) {
        log.debug("Remove leaf with id {{}} from module with id {{}}", leafId, moduleId);
        Module module = repository.findModule(moduleId).orElseThrow(EntityNotFoundException::new);
        module.removeLeaf(leafId);
        repository.save(module);
        return module.getId();
    }
}
