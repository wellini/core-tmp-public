package cc.roadmaps.core.domain.services.course.operations.implementations.moduleCreate;

import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.model.course.Course;
import cc.roadmaps.core.domain.model.course.CourseRepository;
import cc.roadmaps.core.domain.model.module.Module;
import cc.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import cc.roadmaps.core.domain.services.course.operations.Operation;
import cc.roadmaps.core.domain.services.course.operations.commands.CommandType;
import cc.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import cc.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import cc.roadmaps.core.domain.services.user.UserService;

import javax.transaction.Transactional;

public class ModuleCreateOperation extends Operation<ModuleCreateCommand> {

    private final CourseRepository courseRepository;

    public ModuleCreateOperation(UserService userService, CourseAffiliationService courseAffiliationService, CourseRepository courseRepository) {
        super(userService, courseAffiliationService);
        this.courseRepository = courseRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.MODULE_CREATE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, ModuleCreateCommand command) {
        Course course = courseRepository.findCourse(command.getCourseId()).orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Course.class, command.getCourseId()));
        Module module = Module.create(command.getTitle());
        course.addModule(module, command.getOrderId());
        context.setModuleId(module.getId());
        courseRepository.save(course);
        return ExplainedExecResult.identified(context.getModuleId());
    }
}
