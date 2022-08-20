package io.roadmaps.core.domain.services.course.operations.implementations.moduleCreate;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.course.CourseRepository;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.roadmaps.core.domain.services.course.operations.Operation;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContextFactory;
import io.roadmaps.core.domain.services.course.operations.context.implementations.AbstractOperationExecutionContext;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.EntityNotFoundException;

import javax.transaction.Transactional;

public class ModuleCreateOperation extends Operation<ModuleCreateCommand> {

    private final CourseRepository courseRepository;
    private final Generator<Long>  moduleIdSequenceGenerator;

    public ModuleCreateOperation(OperationExecutionContextFactory contextFactory, UserService userService, CourseAffiliationService courseAffiliationService, CourseRepository courseRepository, Generator<Long> moduleIdSequenceGenerator) {
        super(contextFactory, userService, courseAffiliationService);
        this.courseRepository = courseRepository;
        this.moduleIdSequenceGenerator = moduleIdSequenceGenerator;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.MODULE_CREATE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(AbstractOperationExecutionContext context, ModuleCreateCommand command) {
        Course course = courseRepository.findCourse(command.getCourseId()).orElseThrow(EntityNotFoundException::new);
        Module module = Module.create(moduleIdSequenceGenerator, command.getTitle());
        course.addModule(module, command.getOrderId());
        context.setModuleId(module.getId());
        courseRepository.save(course);
        return ExplainedExecResult.identified(context.getModuleId());
    }
}
