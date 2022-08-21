package io.roadmaps.core.domain.services.module;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.module.ModuleRepository;
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
}
