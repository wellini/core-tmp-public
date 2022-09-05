package cc.roadmaps.core.domain.model.module;

import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.model.leaf.Leaf;
import cc.roadmaps.core.domain.model.module.events.EditModuleTitleEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Module {

    @EqualsAndHashCode.Include
    @Getter
    private UUID id;

    @Getter
    private String title;

    @Getter
    private UUID courseId;

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
                .orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Leaf.class, leafId));
        this.leaves.remove(targetLeaf);
    }

    public void editTitle(EditModuleTitleEvent editTitleCommand) {
        title = editTitleCommand.getTitle();
    }

    public void addLeaf(Leaf leaf, int destinationOrderId) {
        leaves.add(Math.min(destinationOrderId, leaves.size()), leaf);
    }

    public boolean hasLeaf(UUID leafId) {
        return leaves.stream().anyMatch(leaf -> leaf.getId().equals(leafId));
    }

    public Leaf getLeaf(UUID leafId) {
        return leaves.stream()
                .filter(leaf -> leaf.getId().equals(leafId))
                .findFirst()
                .orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Leaf.class, leafId));
    }
}
