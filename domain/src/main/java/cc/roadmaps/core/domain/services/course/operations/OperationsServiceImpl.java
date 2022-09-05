package cc.roadmaps.core.domain.services.course.operations;

import cc.roadmaps.core.domain.services.course.operations.commands.Command;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OperationsServiceImpl implements OperationsService {

    private final List<Operation> operations;

    @Override
    public <T extends Command> ExplainedExecResult execute(T command) {
        Operation<Command> operation = operations.stream()
                .filter(op -> op.canExecute(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no handler for command type " + command.getCommandType()));
        return operation.execute(command);
    }
}
