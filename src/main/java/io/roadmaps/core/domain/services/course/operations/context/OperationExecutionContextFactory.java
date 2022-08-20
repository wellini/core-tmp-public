package io.roadmaps.core.domain.services.course.operations.context;

import io.roadmaps.core.domain.services.course.operations.commands.Command;
import io.roadmaps.core.domain.services.course.operations.context.implementations.AbstractOperationExecutionContext;

public interface OperationExecutionContextFactory {

    AbstractOperationExecutionContext createContext(Long currentUserId, Command command);
}
