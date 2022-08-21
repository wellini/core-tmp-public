package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import io.roadmaps.core.domain.model.leaf.events.UpdateTextEvent;
import io.roadmaps.core.domain.model.module.events.LeafCreationEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TextLeaf extends Leaf {

    private String text;

    private TextLeaf(String title, String text) {
        super(title, LeafType.TEXT);
        this.text = text;
    }

    public static TextLeaf create(LeafCreationEvent creationCommand) {
        return new TextLeaf(
                creationCommand.getTitle(),
                creationCommand.getText()
        );
    }

    public void updateText(UpdateTextEvent updateTextCommand) {
        text = updateTextCommand.getText();
    }
}
