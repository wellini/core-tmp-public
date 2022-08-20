package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.domain.model.module.events.LeafCreationEvent;
import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class LinkLeaf extends Leaf {

    private String link;

    public LinkLeaf() {
    }

    private LinkLeaf(Generator<Long> idGenerator, String title, String link) {
        super(idGenerator, title, LeafType.LINK);
        this.link = link;
    }

    public static LinkLeaf create(Generator<Long> idGenerator, LeafCreationEvent creationCommand) {
        return new LinkLeaf(
                idGenerator,
                creationCommand.getTitle(),
                creationCommand.getLink()
        );
    }
}


