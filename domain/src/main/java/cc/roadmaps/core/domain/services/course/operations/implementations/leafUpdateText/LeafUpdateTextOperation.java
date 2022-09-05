package cc.roadmaps.core.domain.services.course.operations.implementations.leafUpdateText;

import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.model.leaf.LeafRepository;
import cc.roadmaps.core.domain.model.leaf.TextLeaf;
import cc.roadmaps.core.domain.model.leaf.enums.LeafType;
import cc.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import cc.roadmaps.core.domain.services.course.operations.Operation;
import cc.roadmaps.core.domain.services.course.operations.commands.CommandType;
import cc.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import cc.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import cc.roadmaps.core.domain.services.user.UserService;

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
        TextLeaf leaf = (TextLeaf) leafRepository.findLeafByIdAndType(command.getLeafId(), LeafType.TEXT)
                .orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(TextLeaf.class, command.getLeafId()));
        leaf.updateText(command);
        leafRepository.save(leaf);
        return ExplainedExecResult.identified(leaf.getId());
    }
}
