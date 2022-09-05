package cc.roadmaps.core.domain.services.course.operations.implementations.courseEditPresentation;

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

public class CourseEditPresentationOperation extends Operation<CourseEditPresentationCommand> {

    private final CourseRepository courseRepository;

    public CourseEditPresentationOperation(UserService userService, CourseAffiliationService courseAffiliationService, CourseRepository courseRepository) {
        super(userService, courseAffiliationService);
        this.courseRepository = courseRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.COURSE_EDIT_PRESENTATION;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, CourseEditPresentationCommand command) {
        Course course = courseRepository.findCourse(command.getCourseId())
                .orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Course.class, command.getCourseId()));
        course.editPresentation(command);
        courseRepository.save(course);
        return ExplainedExecResult.identified(course.getId());
    }
}
