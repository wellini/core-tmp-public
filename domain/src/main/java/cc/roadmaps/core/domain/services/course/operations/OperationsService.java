package cc.roadmaps.core.domain.services.course.operations;

import cc.roadmaps.core.domain.services.course.operations.commands.Command;

public interface OperationsService {

    <T extends Command> ExplainedExecResult execute(T command);
}
