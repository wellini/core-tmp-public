package cc.roadmaps.core.domain.services.course.operations.implementations.leafMove;

import cc.roadmaps.core.domain.model.course.events.MoveLeafEvent;
import cc.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface LeafMoveCommand extends Command, MoveLeafEvent {

    @Override
    UUID getLeafId();
}
