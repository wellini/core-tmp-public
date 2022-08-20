package io.roadmaps.core.domain.services.course.operations.implementations.moduleMove;

import io.roadmaps.core.domain.model.course.events.MoveModuleEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

public interface ModuleMoveCommand extends Command, MoveModuleEvent {

    @Override
    Long getModuleId();
}
