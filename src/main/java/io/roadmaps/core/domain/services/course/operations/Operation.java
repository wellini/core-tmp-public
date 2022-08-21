package io.roadmaps.core.domain.services.course.operations;

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.domain.model.user.User;
import io.roadmaps.core.domain.services.course.operations.commands.Command;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.NotEnoughPermissionsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class Operation<C extends Command> {

    protected final UserService userService;
    protected final CourseAffiliationService courseAffiliationService;

    protected abstract CommandType getCommandType();

    public boolean canExecute(C command) {
        return command.getCommandType() == getCommandType();
    }

    public ExplainedExecResult execute(C command) {
        User currentUser = userService.getCurrentUser();
        OperationExecutionContext context = OperationExecutionContext.create(currentUser.getId(), command);
        if (getCommandType().hasRestrictedAccess()) {
            return executeWithAccessCheck(command, currentUser, context);
        } else {
            return executeDirectly(command, context);
        }
    }

    private ExplainedExecResult executeDirectly(C command, OperationExecutionContext context) {
        return getExplainedExecResult(command, context);
    }

    private ExplainedExecResult executeWithAccessCheck(C command, User currentUser, OperationExecutionContext context) {
        CourseAffiliationType affiliation = courseAffiliationService.safeResolveAffiliation(currentUser.getId(), command);
        if (getCommandType().isAllowedFor(affiliation)) {
            context.setCurrentUserAffiliationType(affiliation);
            return getExplainedExecResult(command, context);
        } else {
            log.info("Not executed [{}] because user with id {{}} has not enough permissions. Context: {}", getCommandType(), context.getCurrentUserId(), context);
            throw new NotEnoughPermissionsException();
        }
    }

    private ExplainedExecResult getExplainedExecResult(C command, OperationExecutionContext context) {
        log.info("Execute [{}]: {}", getCommandType(), getCommandType().getToDoMessage(context));
        try {
            ExplainedExecResult execResult = doExecute(context, command);
            String completedMessage = getCommandType().getCompletedMessage(context);
            log.info("Completed [{}]: {}", getCommandType(), completedMessage);
            execResult.setMessage(completedMessage);
            return execResult;
        } catch (Exception exception) {
            log.info("Not completed [{}] because of exception [{}]. Context: {}", getCommandType(), exception, context);
            throw exception;
        }
    }

    protected abstract ExplainedExecResult doExecute(OperationExecutionContext context, C command);

}
