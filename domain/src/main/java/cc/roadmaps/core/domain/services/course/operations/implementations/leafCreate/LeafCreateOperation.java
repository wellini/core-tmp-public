package cc.roadmaps.core.domain.services.course.operations.implementations.leafCreate;

import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.model.leaf.Leaf;
import cc.roadmaps.core.domain.model.leaf.LeafFactory;
import cc.roadmaps.core.domain.model.module.Module;
import cc.roadmaps.core.domain.model.module.ModuleRepository;
import cc.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import cc.roadmaps.core.domain.services.course.operations.Operation;
import cc.roadmaps.core.domain.services.course.operations.commands.CommandType;
import cc.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import cc.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import cc.roadmaps.core.domain.services.user.UserService;

public class LeafCreateOperation extends Operation<LeafCreateCommand> {

    private final ModuleRepository moduleRepository;

    public LeafCreateOperation(UserService userService, CourseAffiliationService courseAffiliationService, ModuleRepository moduleRepository) {
        super(userService, courseAffiliationService);
        this.moduleRepository = moduleRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.LEAF_CREATE;
    }

    @Override
    protected ExplainedExecResult doExecute(OperationExecutionContext context, LeafCreateCommand command) {
        Module module = moduleRepository.findModule(command.getModuleId())
                .orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Module.class, command.getModuleId()));
        Leaf leaf = LeafFactory.create(command);
        module.addLeaf(leaf, command.getOrderId());
        context.setLeafId(leaf.getId());
        moduleRepository.save(module);
        return ExplainedExecResult.identified(leaf.getId());
    }
}
