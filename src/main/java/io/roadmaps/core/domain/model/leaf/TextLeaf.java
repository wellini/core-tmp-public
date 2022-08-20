package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.domain.model.module.events.LeafCreationEvent;
import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import io.roadmaps.core.domain.model.leaf.events.UpdateTextEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TextLeaf extends Leaf {

    private String text;

    private TextLeaf(Generator<Long> idGenerator, String title, String text) {
        super(idGenerator, title, LeafType.TEXT);
        this.text = text;
    }

    public static TextLeaf create(Generator<Long> idGenerator, LeafCreationEvent creationCommand) {
        return new TextLeaf(
                idGenerator,
                creationCommand.getTitle(),
                creationCommand.getText()
        );
    }

    public void updateText(UpdateTextEvent updateTextCommand) {
        text = updateTextCommand.getText();
    }
}
