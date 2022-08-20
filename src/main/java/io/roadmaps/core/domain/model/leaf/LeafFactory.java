package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.domain.model.module.events.LeafCreationEvent;

public class LeafFactory {

    public static <T extends Leaf> T create(Generator<Long> leafIdSequenceGenerator, LeafCreationEvent creationCommand) {
        switch (creationCommand.getType()) {
            case LINK:
                return (T) LinkLeaf.create(leafIdSequenceGenerator, creationCommand);
            case TEXT:
            default:
                return (T) TextLeaf.create(leafIdSequenceGenerator, creationCommand);
        }
    }

}
