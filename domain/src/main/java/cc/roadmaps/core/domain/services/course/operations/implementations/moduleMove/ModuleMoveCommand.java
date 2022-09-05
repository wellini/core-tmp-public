package cc.roadmaps.core.domain.services.course.operations.implementations.moduleMove;

import cc.roadmaps.core.domain.model.course.events.MoveModuleEvent;
import cc.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface ModuleMoveCommand extends Command, MoveModuleEvent {

    @Override
    UUID getModuleId();
}
