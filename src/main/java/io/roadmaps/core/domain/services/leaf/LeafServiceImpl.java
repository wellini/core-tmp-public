package io.roadmaps.core.domain.services.leaf;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.leaf.LeafRepository;
import io.roadmaps.core.domain.model.leaf.TextLeaf;
import io.roadmaps.core.domain.model.leaf.commands.EditLeafTitleCommand;
import io.roadmaps.core.domain.model.leaf.commands.UpdateTextCommand;
import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.services.module.ModuleService;
import io.roadmaps.core.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class LeafServiceImpl implements LeafService {

    private final LeafRepository repository;

    @Override
    public Leaf getLeaf(UUID id) {
        return repository.findLeaf(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UUID editTitle(UUID id, EditLeafTitleCommand editLeafTitleCommand) {
        log.debug("Edit title of leaf with id {{}}", id);
        Leaf leaf = repository.findLeaf(id).orElseThrow(EntityNotFoundException::new);
        leaf.editTitle(editLeafTitleCommand);
        repository.save(leaf);
        return leaf.getId();
    }

    @Override
    public UUID updateText(UUID id, UpdateTextCommand updateTextCommand) {
        log.debug("Update text in leaf with id {{}}", id);
        TextLeaf leaf = (TextLeaf) repository.findLeafByIdAndType(id, LeafType.TEXT).orElseThrow(EntityNotFoundException::new);
        leaf.updateText(updateTextCommand);
        repository.save(leaf);
        return leaf.getId();
    }
}
