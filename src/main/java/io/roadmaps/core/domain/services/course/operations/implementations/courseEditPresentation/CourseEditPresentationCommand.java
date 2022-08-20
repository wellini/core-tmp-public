package io.roadmaps.core.domain.services.course.operations.implementations.courseEditPresentation;

import io.roadmaps.core.domain.model.course.presentation.events.EditPresentationEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

public interface CourseEditPresentationCommand extends Command, EditPresentationEvent {

    @Override
    Long getCourseId();
}
