package io.roadmaps.core.domain.model.leaf;

import io.roadmaps.core.domain.model.module.commands.LeafCreationCommand;

public class LeafFactory {

    public static <T extends Leaf> T create(LeafCreationCommand creationCommand) {
        switch (creationCommand.getType()) {
            case LINK:
                return (T) LinkLeaf.create(creationCommand);
            case TEXT:
            default:
                return (T) TextLeaf.create(creationCommand);
        }
    }

}
