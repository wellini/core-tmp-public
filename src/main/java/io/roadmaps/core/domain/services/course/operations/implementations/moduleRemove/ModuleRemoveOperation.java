package io.roadmaps.core.domain.services.course.operations.implementations.moduleRemove;

import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.course.CourseRepository;
import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.roadmaps.core.domain.services.course.operations.Operation;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContextFactory;
import io.roadmaps.core.domain.services.course.operations.context.implementations.AbstractOperationExecutionContext;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.EntityNotFoundException;

import javax.transaction.Transactional;

public class ModuleRemoveOperation extends Operation<ModuleRemoveCommand> {

    private final CourseRepository courseRepository;

    public ModuleRemoveOperation(OperationExecutionContextFactory contextFactory, UserService userService, CourseAffiliationService courseAffiliationService, CourseRepository courseRepository) {
        super(contextFactory, userService, courseAffiliationService);
        this.courseRepository = courseRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.MODULE_REMOVE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(AbstractOperationExecutionContext context, ModuleRemoveCommand command) {
        Course course = courseRepository.findCourseByModuleId(command.getModuleId()).orElseThrow(EntityNotFoundException::new);
        context.setCourseId(course.getId());
        course.removeModule(command.getModuleId());
        courseRepository.save(course);
        return ExplainedExecResult.empty();
    }
}
