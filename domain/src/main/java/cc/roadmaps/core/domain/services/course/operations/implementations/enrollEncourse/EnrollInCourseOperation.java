package cc.roadmaps.core.domain.services.course.operations.implementations.enrollEncourse;

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

public class EnrollInCourseOperation extends Operation<EnrollInCourseCommand> {

    private final CourseRepository courseRepository;

    public EnrollInCourseOperation(UserService userService, CourseAffiliationService courseAffiliationService, CourseRepository courseRepository) {
        super(userService, courseAffiliationService);
        this.courseRepository = courseRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.ENROLL_IN_COURSE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, EnrollInCourseCommand command) {
        Course course = courseRepository.findCourse(command.getCourseId()).orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Course.class, command.getCourseId()));
        course.enrollInCourse(context.getCurrentUserId());
        courseRepository.save(course);
        context.setCurrentUserAffiliationType(course.getAffiliationType(context.getCurrentUserId()));
        return ExplainedExecResult.identified(course.getId());
    }
}
