package io.roadmaps.core.domain.services.course.operations.implementations.leafUpdateText;

import io.roadmaps.core.domain.model.leaf.LeafRepository;
import io.roadmaps.core.domain.model.leaf.TextLeaf;
import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.roadmaps.core.domain.services.course.operations.Operation;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.EntityNotFoundException;

import javax.transaction.Transactional;

public class LeafUpdateTextOperation extends Operation<LeafUpdateTextCommand> {

    private final LeafRepository leafRepository;

    public LeafUpdateTextOperation(UserService userService, CourseAffiliationService courseAffiliationService, LeafRepository leafRepository) {
        super(userService, courseAffiliationService);
        this.leafRepository = leafRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.LEAF_UPDATE_TEXT;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, LeafUpdateTextCommand command) {
        TextLeaf leaf = (TextLeaf) leafRepository.findLeafByIdAndType(command.getLeafId(), LeafType.TEXT).orElseThrow(EntityNotFoundException::new);
        leaf.updateText(command);
        leafRepository.save(leaf);
        return ExplainedExecResult.identified(leaf.getId());
    }
}
