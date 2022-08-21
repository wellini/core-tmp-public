package io.roadmaps.core.domain.services.course.operations.implementations.courseRemove;

import io.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface CourseRemoveCommand extends Command {

    @Override
    UUID getCourseId();
}
