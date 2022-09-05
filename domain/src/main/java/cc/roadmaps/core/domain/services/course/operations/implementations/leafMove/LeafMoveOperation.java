package cc.roadmaps.core.domain.services.course.operations.implementations.leafMove;

import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.model.course.Course;
import cc.roadmaps.core.domain.model.course.CourseRepository;
import cc.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import cc.roadmaps.core.domain.services.course.operations.Operation;
import cc.roadmaps.core.domain.services.course.operations.commands.CommandType;
import cc.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import cc.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import cc.roadmaps.core.domain.services.user.UserService;

import javax.transaction.Transactional;

public class LeafMoveOperation extends Operation<LeafMoveCommand> {

    private final CourseRepository courseRepository;

    public LeafMoveOperation(UserService userService, CourseAffiliationService courseAffiliationService, CourseRepository courseRepository) {
        super(userService, courseAffiliationService);
        this.courseRepository = courseRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.LEAF_MOVE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, LeafMoveCommand command) {
        Course course = courseRepository.findCourseByLeafId(command.getLeafId())
                .orElseThrow(EntityNotFoundDomainException.createSimpleExceptionSupplier(Course.class));
        course.moveLeaf(command.getLeafId(), command);
        courseRepository.save(course);
        return ExplainedExecResult.identified(command.getLeafId());
    }
}
