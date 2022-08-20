package io.roadmaps.core.domain.services.course.operations.context.factories;

import io.roadmaps.core.domain.common.id.IdExplanationFormatter;
import io.roadmaps.core.domain.services.course.operations.commands.Command;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContextFactory;
import io.roadmaps.core.domain.services.course.operations.context.implementations.AbstractOperationExecutionContext;
import io.roadmaps.core.domain.services.course.operations.context.implementations.FormattedOperationExecutionContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FormattedOperationExecutionContextFactory implements OperationExecutionContextFactory {

    private final IdExplanationFormatter formatter;

    @Override
    public AbstractOperationExecutionContext createContext(Long currentUserId, Command command) {
        return new FormattedOperationExecutionContext(
                formatter,
                currentUserId,
                null,
                command.getCourseId(),
                command.getModuleId(),
                command.getLeafId()
        );
    }
}
