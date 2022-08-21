package io.roadmaps.core.domain.services.course.operations.implementations.leafCreate;

import io.roadmaps.core.domain.model.module.events.LeafCreationEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface LeafCreateCommand extends Command, LeafCreationEvent {

    @Override
    UUID getModuleId();
}
