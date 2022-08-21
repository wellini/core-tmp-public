package io.roadmaps.core.domain.services.course.operations.implementations.courseEditPresentation;

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
        Course course = courseRepository.findCourse(command.getCourseId()).orElseThrow(EntityNotFoundException::new);
        course.editPresentation(command);
        courseRepository.save(course);
        return ExplainedExecResult.identified(course.getId());
    }
}
