package io.roadmaps.core.domain.services.course.operations.context.factories;

import io.roadmaps.core.domain.services.course.operations.commands.Command;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContextFactory;
import io.roadmaps.core.domain.services.course.operations.context.implementations.AbstractOperationExecutionContext;
import io.roadmaps.core.domain.services.course.operations.context.implementations.SimpleOperationExecutionContext;
import io.roadmaps.core.domain.services.user.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleOperationExecutionContextFactory implements OperationExecutionContextFactory {

    private final UserService userService;

    @Override
    public AbstractOperationExecutionContext createContext(Long currentUserId, Command command) {
        return new SimpleOperationExecutionContext(
                userService.getCurrentUser().getId(),
                null,
                command.getCourseId(),
                command.getModuleId(),
                command.getLeafId()
        );
    }
}
