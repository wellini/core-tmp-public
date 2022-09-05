package cc.roadmaps.core.domain.services.course.operations.implementations.courseRemove;

import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.model.course.Course;
import cc.roadmaps.core.domain.model.course.CourseRepository;
import cc.roadmaps.core.domain.model.courseAffiliation.CourseAffiliationRepository;
import cc.roadmaps.core.domain.model.leaf.LeafRepository;
import cc.roadmaps.core.domain.model.module.ModuleRepository;
import cc.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import cc.roadmaps.core.domain.services.course.operations.Operation;
import cc.roadmaps.core.domain.services.course.operations.commands.CommandType;
import cc.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import cc.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import cc.roadmaps.core.domain.services.user.UserService;

import javax.transaction.Transactional;

public class CourseRemoveOperation extends Operation<CourseRemoveCommand> {

    private final LeafRepository leafRepository;
    private final ModuleRepository moduleRepository;
    private final CourseAffiliationRepository courseAffiliationRepository;
    private final CourseRepository courseRepository;

    public CourseRemoveOperation(UserService userService, CourseAffiliationService courseAffiliationService, LeafRepository leafRepository, ModuleRepository moduleRepository, CourseAffiliationRepository courseAffiliationRepository, CourseRepository courseRepository) {
        super(userService, courseAffiliationService);
        this.leafRepository = leafRepository;
        this.moduleRepository = moduleRepository;
        this.courseAffiliationRepository = courseAffiliationRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.COURSE_REMOVE;
    }

    @Override
    @Transactional
    protected ExplainedExecResult doExecute(OperationExecutionContext context, CourseRemoveCommand command) {
        Course course = courseRepository.findCourse(command.getCourseId())
                .orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Course.class, command.getCourseId()));
        leafRepository.deleteAllByCourseId(course.getId());
        moduleRepository.deleteAllByCourseId(course.getId());
        courseAffiliationRepository.deleteAllByCourseId(course.getId());
        courseRepository.delete(course.getId());
        return ExplainedExecResult.empty();
    }
}
