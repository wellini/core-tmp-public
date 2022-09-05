package cc.roadmaps.core.domain.services.course.operations.implementations.leafEditTitle;

import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.model.leaf.Leaf;
import cc.roadmaps.core.domain.model.leaf.LeafRepository;
import cc.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import cc.roadmaps.core.domain.services.course.operations.Operation;
import cc.roadmaps.core.domain.services.course.operations.commands.CommandType;
import cc.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import cc.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import cc.roadmaps.core.domain.services.user.UserService;

import javax.transaction.Transactional;

public class LeafEditTitleOperation extends Operation<LeafEditTitleCommand> {

    private final LeafRepository leafRepository;

    public LeafEditTitleOperation(UserService userService, CourseAffiliationService courseAffiliationService, LeafRepository leafRepository) {
        super(userService, courseAffiliationService);
        this.leafRepository = leafRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.LEAF_EDIT_TITLE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, LeafEditTitleCommand command) {
        Leaf leaf = leafRepository.findLeaf(command.getLeafId()).orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Leaf.class, command.getLeafId()));
        leaf.editTitle(command);
        leafRepository.save(leaf);
        return ExplainedExecResult.identified(leaf.getId());
    }
}
