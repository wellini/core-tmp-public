package cc.roadmaps.core.domain.model.leaf;

import cc.roadmaps.core.domain.model.module.events.LeafCreationEvent;

public class LeafFactory {

    public static <T extends Leaf> T create(LeafCreationEvent creationCommand) {
        switch (creationCommand.getType()) {
            case LINK:
                return (T) LinkLeaf.create(creationCommand);
            case TEXT:
            default:
                return (T) TextLeaf.create(creationCommand);
        }
    }

}
