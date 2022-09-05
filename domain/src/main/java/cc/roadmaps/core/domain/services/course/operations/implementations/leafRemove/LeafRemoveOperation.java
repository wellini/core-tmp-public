package cc.roadmaps.core.domain.services.course.operations.implementations.leafRemove;

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

public class LeafRemoveOperation extends Operation<LeafRemoveCommand> {

    private final ModuleRepository moduleRepository;

    public LeafRemoveOperation(UserService userService, CourseAffiliationService courseAffiliationService, ModuleRepository moduleRepository) {
        super(userService, courseAffiliationService);
        this.moduleRepository = moduleRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.LEAF_REMOVE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, LeafRemoveCommand command) {
        Module module = moduleRepository.findModuleByLeafId(command.getLeafId()).orElseThrow(EntityNotFoundDomainException.createSimpleExceptionSupplier(Module.class));
        context.setModuleId(module.getId());
        module.removeLeaf(command.getLeafId());
        moduleRepository.save(module);
        return ExplainedExecResult.empty();
    }
}
