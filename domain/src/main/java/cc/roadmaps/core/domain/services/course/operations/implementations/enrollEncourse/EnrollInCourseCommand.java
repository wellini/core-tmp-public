package cc.roadmaps.core.domain.services.course.operations.implementations.enrollEncourse;

import cc.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface EnrollInCourseCommand extends Command {

    @Override
    UUID getCourseId();
}
