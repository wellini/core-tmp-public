package io.roadmaps.core.domain.services.course.operations.utils


import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult
import io.roadmaps.core.domain.services.course.operations.Operation
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContextFactory
import io.roadmaps.core.domain.services.course.operations.context.implementations.AbstractOperationExecutionContext
import io.roadmaps.core.domain.services.course.operations.context.implementations.SimpleOperationExecutionContext
import io.roadmaps.core.domain.services.course.operations.commands.CommandType
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService

class TestOperation extends Operation<TestCommand> {

    def CommandType commandType

    private final ControlActionService service

    TestOperation(OperationExecutionContextFactory contextFactory, UserService userService, CourseAffiliationService courseAffiliationService, ControlActionService service) {
        super(contextFactory, userService, courseAffiliationService)
        this.service = service
    }

    @Override
    protected CommandType getCommandType() {
        return commandType
    }

    @Override
    protected ExplainedExecResult doExecute(AbstractOperationExecutionContext context, TestCommand command) {
        service.doControlAction()
        return ExplainedExecResult.empty()
    }

    public static interface ControlActionService {
        void doControlAction()
    }
}
