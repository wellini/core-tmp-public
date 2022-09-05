package cc.roadmaps.core.domain.services.course.operations.implementations.courseEditPresentation;

import cc.roadmaps.core.domain.model.course.presentation.events.EditPresentationEvent;
import cc.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface CourseEditPresentationCommand extends Command, EditPresentationEvent {

    @Override
    UUID getCourseId();
}
