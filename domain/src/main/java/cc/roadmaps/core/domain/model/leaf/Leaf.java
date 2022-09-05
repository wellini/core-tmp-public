package cc.roadmaps.core.domain.model.leaf;

import cc.roadmaps.core.domain.model.leaf.enums.LeafType;
import cc.roadmaps.core.domain.model.leaf.events.EditLeafTitleEvent;
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

    public void editTitle(EditLeafTitleEvent editTitleCommand) {
        this.title = editTitleCommand.getTitle();
    }
}
