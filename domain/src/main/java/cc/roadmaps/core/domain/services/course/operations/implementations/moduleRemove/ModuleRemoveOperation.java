package cc.roadmaps.core.domain.services.course.operations.implementations.moduleRemove;

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

public class ModuleRemoveOperation extends Operation<ModuleRemoveCommand> {

    private final CourseRepository courseRepository;

    public ModuleRemoveOperation(UserService userService, CourseAffiliationService courseAffiliationService, CourseRepository courseRepository) {
        super(userService, courseAffiliationService);
        this.courseRepository = courseRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.MODULE_REMOVE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, ModuleRemoveCommand command) {
        Course course = courseRepository.findCourseByModuleId(command.getModuleId()).orElseThrow(EntityNotFoundDomainException.createSimpleExceptionSupplier(Course.class));
        context.setCourseId(course.getId());
        course.removeModule(command.getModuleId());
        courseRepository.save(course);
        return ExplainedExecResult.empty();
    }
}
