package io.roadmaps.core.domain.services.course.operations.implementations.leafMove;

import io.roadmaps.core.domain.model.course.events.MoveLeafEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface LeafMoveCommand extends Command, MoveLeafEvent {

    @Override
    UUID getLeafId();
}
