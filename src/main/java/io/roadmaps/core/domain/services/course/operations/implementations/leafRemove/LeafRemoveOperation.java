package io.roadmaps.core.domain.services.course.operations.implementations.leafRemove;

import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.module.ModuleRepository;
import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.roadmaps.core.domain.services.course.operations.Operation;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContextFactory;
import io.roadmaps.core.domain.services.course.operations.context.implementations.AbstractOperationExecutionContext;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.EntityNotFoundException;

import javax.transaction.Transactional;

public class LeafRemoveOperation extends Operation<LeafRemoveCommand> {

    private final ModuleRepository moduleRepository;

    public LeafRemoveOperation(OperationExecutionContextFactory contextFactory, UserService userService, CourseAffiliationService courseAffiliationService, ModuleRepository moduleRepository) {
        super(contextFactory, userService, courseAffiliationService);
        this.moduleRepository = moduleRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.LEAF_REMOVE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(AbstractOperationExecutionContext context, LeafRemoveCommand command) {
        Module module = moduleRepository.findModuleByLeafId(command.getLeafId()).orElseThrow(EntityNotFoundException::new);
        context.setModuleId(module.getId());
        module.removeLeaf(command.getLeafId());
        moduleRepository.save(module);
        return ExplainedExecResult.empty();
    }
}
