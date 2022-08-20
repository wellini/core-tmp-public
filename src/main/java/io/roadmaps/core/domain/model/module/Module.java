package io.roadmaps.core.domain.model.module;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.module.events.EditModuleTitleEvent;
import io.roadmaps.core.exception.EntityNotFoundException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Module {

    @EqualsAndHashCode.Include
    @Getter
    private Long id;

    @Getter
    private String title;

    @Getter
    private Long courseId;

    @Getter
    @Setter
    private List<Leaf> leaves = Collections.emptyList();

    public static Module create(Generator<Long> idGenerator, String title) {
        return new Module(idGenerator, title);
    }

    private Module(Generator<Long> idGenerator, String title) {
        this.id = idGenerator.generateNext();
        this.title = title;
    }

    public void removeLeaf(Long leafId) {
        Leaf targetLeaf = leaves.stream()
                .filter(leaf -> leaf.getId().equals(leafId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        this.leaves.remove(targetLeaf);
    }

    public void editTitle(EditModuleTitleEvent editTitleCommand) {
        title = editTitleCommand.getTitle();
    }

    public void addLeaf(Leaf leaf, int destinationOrderId) {
        leaves.add(Math.min(destinationOrderId, leaves.size()), leaf);
    }

    public boolean hasLeaf(Long leafId) {
        return leaves.stream().anyMatch(leaf -> leaf.getId().equals(leafId));
    }

    public Leaf getLeaf(Long leafId) {
        return leaves.stream()
                .filter(leaf -> leaf.getId().equals(leafId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }
}
