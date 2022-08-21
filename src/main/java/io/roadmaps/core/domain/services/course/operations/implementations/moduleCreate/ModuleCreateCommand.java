package io.roadmaps.core.domain.services.course.operations.implementations.moduleCreate;

import io.roadmaps.core.domain.model.course.events.ModuleCreationEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface ModuleCreateCommand extends Command, ModuleCreationEvent {

    @Override
    UUID getCourseId();
}
