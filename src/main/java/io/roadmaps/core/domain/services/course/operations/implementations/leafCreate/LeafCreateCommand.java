package io.roadmaps.core.domain.services.course.operations.implementations.leafCreate;

import io.roadmaps.core.domain.model.module.events.LeafCreationEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

public interface LeafCreateCommand extends Command, LeafCreationEvent {

    @Override
    Long getModuleId();
}
