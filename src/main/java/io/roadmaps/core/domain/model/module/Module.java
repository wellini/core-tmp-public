package io.roadmaps.core.domain.model.module;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.leaf.LeafFactory;
import io.roadmaps.core.domain.model.module.commands.EditModuleTitleCommand;
import io.roadmaps.core.domain.model.module.commands.LeafCreationCommand;
import io.roadmaps.core.exception.EntityNotFoundException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Module {

    @EqualsAndHashCode.Include
    @Getter
    private UUID id;

    @Getter
    private UUID courseId;

    @Getter
    private String title;

    @Getter
    @Setter
    private List<Leaf> leaves = Collections.emptyList();

    public static Module create(String title) {
        return new Module(title);
    }

    private Module(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
    }

    public void removeLeaf(UUID leafId) {
        Leaf targetLeaf = leaves.stream()
                .filter(leaf -> leaf.getId().equals(leafId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        this.leaves.remove(targetLeaf);
    }

    public void editTitle(EditModuleTitleCommand editTitleCommand) {
        title = editTitleCommand.getTitle();
    }

    public UUID addLeaf(LeafCreationCommand leafCreationCommand) {
        Leaf leaf = LeafFactory.create(leafCreationCommand);
        if(leafCreationCommand.getOrderId() < this.leaves.size()) {
            this.leaves.add(leafCreationCommand.getOrderId(), leaf);
        } else {
            this.leaves.add(leaf);
        }

        return leaf.getId();
    }
}
