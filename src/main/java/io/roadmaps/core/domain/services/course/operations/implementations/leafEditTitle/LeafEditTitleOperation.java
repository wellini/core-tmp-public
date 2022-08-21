package io.roadmaps.core.domain.services.course.operations.implementations.leafEditTitle;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.leaf.LeafRepository;
import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.roadmaps.core.domain.services.course.operations.Operation;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.EntityNotFoundException;

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
        Leaf leaf = leafRepository.findLeaf(command.getLeafId()).orElseThrow(EntityNotFoundException::new);
        leaf.editTitle(command);
        leafRepository.save(leaf);
        return ExplainedExecResult.identified(leaf.getId());
    }
}
