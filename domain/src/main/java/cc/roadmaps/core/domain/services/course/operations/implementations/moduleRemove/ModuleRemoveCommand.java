package cc.roadmaps.core.domain.services.course.operations.implementations.moduleRemove;

import cc.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface ModuleRemoveCommand extends Command {

    @Override
    UUID getModuleId();
}
