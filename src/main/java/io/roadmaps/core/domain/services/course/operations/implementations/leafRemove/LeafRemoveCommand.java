package io.roadmaps.core.domain.services.course.operations.implementations.leafRemove;

import io.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface LeafRemoveCommand extends Command {

    @Override
    UUID getLeafId();
}
