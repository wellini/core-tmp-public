package io.roadmaps.core.domain.services.course.operations.utils

import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult
import io.roadmaps.core.domain.services.course.operations.Operation
import io.roadmaps.core.domain.services.course.operations.commands.CommandType
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService

class TestOperation extends Operation<TestCommand> {

    def CommandType commandType

    private final ControlActionService service

    TestOperation(UserService userService, CourseAffiliationService courseAffiliationService, ControlActionService service) {
        super(userService, courseAffiliationService)
        this.service = service
    }

    @Override
    protected CommandType getCommandType() {
        return commandType
    }

    @Override
    protected ExplainedExecResult doExecute(OperationExecutionContext context, TestCommand command) {
        service.doControlAction()
        return ExplainedExecResult.empty()
    }

    public static interface ControlActionService {
        void doControlAction()
    }
}
