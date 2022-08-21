package io.roadmaps.core.domain.services.course.operations.implementations.leafMove;

import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.course.CourseRepository;
import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.roadmaps.core.domain.services.course.operations.Operation;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.EntityNotFoundException;

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
                .orElseThrow(EntityNotFoundException::new);
        course.moveLeaf(command.getLeafId(), command);
        courseRepository.save(course);
        return ExplainedExecResult.identified(command.getLeafId());
    }
}
