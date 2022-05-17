package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.model.module.commands.LeafCreationCommand;
import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import io.roadmaps.core.domain.model.leaf.commands.UpdateTextCommand;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TextLeaf extends Leaf {

    private String text;

    private TextLeaf(String title, String text) {
        super(title, LeafType.TEXT);
        this.text = text;
    }

    public static TextLeaf create(LeafCreationCommand creationCommand) {
        return new TextLeaf(
                creationCommand.getTitle(),
                creationCommand.getText()
        );
    }

    public void updateText(UpdateTextCommand updateTextCommand) {
        text = updateTextCommand.getText();
    }
}
