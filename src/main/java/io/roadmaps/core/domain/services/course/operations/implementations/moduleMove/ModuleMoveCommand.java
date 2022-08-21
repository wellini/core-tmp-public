package io.roadmaps.core.domain.services.course.operations.implementations.moduleMove;

import io.roadmaps.core.domain.model.course.events.MoveModuleEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface ModuleMoveCommand extends Command, MoveModuleEvent {

    @Override
    UUID getModuleId();
}
