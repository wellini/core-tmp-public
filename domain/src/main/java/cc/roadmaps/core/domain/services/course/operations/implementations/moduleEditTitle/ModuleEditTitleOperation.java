package cc.roadmaps.core.domain.services.course.operations.implementations.moduleEditTitle;


import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.model.module.Module;
import cc.roadmaps.core.domain.model.module.ModuleRepository;
import cc.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import cc.roadmaps.core.domain.services.course.operations.Operation;
import cc.roadmaps.core.domain.services.course.operations.commands.CommandType;
import cc.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import cc.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import cc.roadmaps.core.domain.services.user.UserService;

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
        Module module = repository.findModule(command.getModuleId()).orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Module.class, command.getModuleId()));
        module.editTitle(command);
        repository.save(module);
        return ExplainedExecResult.identified(module.getId());
    }
}
