package cc.roadmaps.core.domain.services.course.operations.implementations.leafCreate;

import cc.roadmaps.core.domain.model.module.events.LeafCreationEvent;
import cc.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface LeafCreateCommand extends Command, LeafCreationEvent {

    @Override
    UUID getModuleId();
}
