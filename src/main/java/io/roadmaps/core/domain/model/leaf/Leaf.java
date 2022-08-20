package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.domain.model.leaf.events.EditLeafTitleEvent;
import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Leaf {

    @EqualsAndHashCode.Include
    protected Long id;

    protected String title;

    protected LeafType type;

    protected Long moduleId;

    protected Leaf(Generator<Long> idGenerator, String title, LeafType type) {
        id = idGenerator.generateNext();
        this.title = title;
        this.type = type;
    }

    public void editTitle(EditLeafTitleEvent editTitleCommand) {
        this.title = editTitleCommand.getTitle();
    }
}
