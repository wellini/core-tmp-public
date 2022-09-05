package cc.roadmaps.core.domain.services.course.operations.implementations.createCourse;

import cc.roadmaps.core.domain.model.course.Course;
import cc.roadmaps.core.domain.model.course.CourseRepository;
import cc.roadmaps.core.domain.model.user.User;
import cc.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import cc.roadmaps.core.domain.services.course.operations.Operation;
import cc.roadmaps.core.domain.services.course.operations.commands.CommandType;
import cc.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import cc.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import cc.roadmaps.core.domain.services.user.UserService;

import javax.transaction.Transactional;

public class CreateCourseOperation extends Operation<CreateCourseCommand> {

    private final CourseRepository courseRepository;

    public CreateCourseOperation(UserService userService, CourseAffiliationService courseAffiliationService, CourseRepository courseRepository) {
        super(userService, courseAffiliationService);
        this.courseRepository = courseRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.COURSE_CREATE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, CreateCourseCommand command) {
        User author = userService.getCurrentUser();
        Course course = Course.create(author, command);
        context.setCourseId(course.getId());
        courseRepository.save(course);
        return ExplainedExecResult.identified(course.getId());
    }
}
