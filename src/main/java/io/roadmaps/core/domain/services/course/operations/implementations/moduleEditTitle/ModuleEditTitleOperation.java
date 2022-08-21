package io.roadmaps.core.domain.services.course.operations.implementations.moduleEditTitle;

import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.module.ModuleRepository;
import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.roadmaps.core.domain.services.course.operations.Operation;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.EntityNotFoundException;

import javax.transaction.Transactional;

public class ModuleEditTitleOperation extends Operation<ModuleEditTitleCommand> {

    private final ModuleRepository repository;

    public ModuleEditTitleOperation(UserService userService, CourseAffiliationService courseAffiliationService, ModuleRepository repository) {
        super(userService, courseAffiliationService);
        this.repository = repository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.MODULE_EDIT_TITLE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, ModuleEditTitleCommand command) {
        Module module = repository.findModule(command.getModuleId()).orElseThrow(EntityNotFoundException::new);
        module.editTitle(command);
        repository.save(module);
        return ExplainedExecResult.identified(module.getId());
    }
}
