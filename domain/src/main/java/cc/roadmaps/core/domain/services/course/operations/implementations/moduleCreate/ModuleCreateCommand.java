package cc.roadmaps.core.domain.services.course.operations.implementations.moduleCreate;

import cc.roadmaps.core.domain.model.course.events.ModuleCreationEvent;
import cc.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface ModuleCreateCommand extends Command, ModuleCreationEvent {

    @Override
    UUID getCourseId();
}
