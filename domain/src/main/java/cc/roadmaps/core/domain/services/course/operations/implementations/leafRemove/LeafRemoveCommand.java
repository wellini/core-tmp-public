package cc.roadmaps.core.domain.services.course.operations.implementations.leafRemove;

import cc.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface LeafRemoveCommand extends Command {

    @Override
    UUID getLeafId();
}
