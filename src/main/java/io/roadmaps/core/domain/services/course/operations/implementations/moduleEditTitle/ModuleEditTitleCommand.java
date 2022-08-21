package io.roadmaps.core.domain.services.course.operations.implementations.moduleEditTitle;

import io.roadmaps.core.domain.model.module.events.EditModuleTitleEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface ModuleEditTitleCommand extends Command, EditModuleTitleEvent {

    @Override
    UUID getModuleId();
}
