package io.roadmaps.core.domain.services.course.operations.implementations.createCourse;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.course.CourseRepository;
import io.roadmaps.core.domain.model.user.User;
import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.roadmaps.core.domain.services.course.operations.Operation;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContextFactory;
import io.roadmaps.core.domain.services.course.operations.context.implementations.AbstractOperationExecutionContext;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.user.UserService;

import javax.transaction.Transactional;

public class CreateCourseOperation extends Operation<CreateCourseCommand> {

    private final CourseRepository courseRepository;
    private final Generator<Long> courseIdSequenceGenerator;

    public CreateCourseOperation(OperationExecutionContextFactory contextFactory, UserService userService, CourseAffiliationService courseAffiliationService, CourseRepository courseRepository, Generator<Long> courseIdSequenceGenerator) {
        super(contextFactory, userService, courseAffiliationService);
        this.courseRepository = courseRepository;
        this.courseIdSequenceGenerator = courseIdSequenceGenerator;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.COURSE_CREATE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(AbstractOperationExecutionContext context, CreateCourseCommand command) {
        User author = userService.getCurrentUser();
        Course course = Course.create(courseIdSequenceGenerator, author, command);
        context.setCourseId(course.getId());
        courseRepository.save(course);
        return ExplainedExecResult.identified(course.getId());
    }
}
