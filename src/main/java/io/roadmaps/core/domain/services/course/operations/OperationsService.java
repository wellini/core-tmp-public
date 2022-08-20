package io.roadmaps.core.domain.services.course.operations;

import io.roadmaps.core.domain.services.course.operations.commands.Command;

public interface OperationsService {

    <T extends Command> ExplainedExecResult execute(T command);
}
