package io.roadmaps.core.domain.services.course.operations.implementations.moduleEditTitle;

import io.roadmaps.core.domain.model.module.events.EditModuleTitleEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

public interface ModuleEditTitleCommand extends Command, EditModuleTitleEvent {

    @Override
    Long getModuleId();
}
