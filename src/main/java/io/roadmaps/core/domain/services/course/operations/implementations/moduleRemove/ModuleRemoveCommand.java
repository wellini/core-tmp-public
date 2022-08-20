package io.roadmaps.core.domain.services.course.operations.implementations.moduleRemove;

import io.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface ModuleRemoveCommand extends Command {

    @Override
    Long getModuleId();
}
