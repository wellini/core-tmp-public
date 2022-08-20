package io.roadmaps.core.domain.services.course.operations.implementations.courseRemove;

import io.roadmaps.core.domain.services.course.operations.commands.Command;

public interface CourseRemoveCommand extends Command {

    @Override
    Long getCourseId();
}
