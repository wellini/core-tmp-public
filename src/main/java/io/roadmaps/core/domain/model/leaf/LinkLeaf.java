package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import io.roadmaps.core.domain.model.module.events.LeafCreationEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class LinkLeaf extends Leaf {

    private String link;

    public LinkLeaf() {
    }

    private LinkLeaf(String title, String link) {
        super(title, LeafType.LINK);
        this.link = link;
    }

    public static LinkLeaf create(LeafCreationEvent creationCommand) {
        return new LinkLeaf(
                creationCommand.getTitle(),
                creationCommand.getLink()
        );
    }
}


