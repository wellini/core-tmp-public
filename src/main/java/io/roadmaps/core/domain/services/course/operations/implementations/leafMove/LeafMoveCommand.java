package io.roadmaps.core.domain.services.course.operations.implementations.leafMove;

import io.roadmaps.core.domain.model.course.events.MoveLeafEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

public interface LeafMoveCommand extends Command, MoveLeafEvent {

    @Override
    Long getLeafId();
}
