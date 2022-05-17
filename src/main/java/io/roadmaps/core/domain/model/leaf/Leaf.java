package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.model.leaf.commands.EditLeafTitleCommand;
import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Leaf {

    @EqualsAndHashCode.Include
    protected UUID id;

    protected String title;

    protected LeafType type;

    protected UUID moduleId;

    protected Leaf(String title, LeafType type) {
        id = UUID.randomUUID();
        this.title = title;
        this.type = type;
    }

    public void editTitle(EditLeafTitleCommand editTitleCommand) {
        this.title = editTitleCommand.getTitle();
    }
}
