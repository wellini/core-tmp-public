package cc.roadmaps.core.domain.services.course.operations.implementations.moduleMove;

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

public class ModuleMoveOperation extends Operation<ModuleMoveCommand> {

    private final CourseRepository courseRepository;

    public ModuleMoveOperation(UserService userService, CourseAffiliationService courseAffiliationService, CourseRepository courseRepository) {
        super(userService, courseAffiliationService);
        this.courseRepository = courseRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.MODULE_MOVE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, ModuleMoveCommand command) {
        Course course = courseRepository.findCourseByModuleId(command.getModuleId())
                .orElseThrow(EntityNotFoundDomainException.createSimpleExceptionSupplier(Course.class));
        course.moveModule(command.getModuleId(), command);
        courseRepository.save(course);
        return ExplainedExecResult.identified(command.getModuleId());
    }
}
