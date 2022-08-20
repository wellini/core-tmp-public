package io.roadmaps.core.domain.services.course.operations.implementations.leafCreate;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.leaf.LeafFactory;
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

public class LeafCreateOperation extends Operation<LeafCreateCommand> {

    private final ModuleRepository moduleRepository;
    private final Generator<Long> leafIdSequenceGenerator;

    public LeafCreateOperation(OperationExecutionContextFactory contextFactory, UserService userService, CourseAffiliationService courseAffiliationService, ModuleRepository moduleRepository, Generator<Long> leafIdSequenceGenerator) {
        super(contextFactory, userService, courseAffiliationService);
        this.moduleRepository = moduleRepository;
        this.leafIdSequenceGenerator = leafIdSequenceGenerator;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.LEAF_CREATE;
    }

    @Override
    protected ExplainedExecResult doExecute(AbstractOperationExecutionContext context, LeafCreateCommand command) {
        Module module = moduleRepository.findModule(command.getModuleId()).orElseThrow(EntityNotFoundException::new);
        Leaf leaf = LeafFactory.create(leafIdSequenceGenerator, command);
        module.addLeaf(leaf, command.getOrderId());
        context.setLeafId(leaf.getId());
        moduleRepository.save(module);
        return ExplainedExecResult.identified(leaf.getId());
    }
}
