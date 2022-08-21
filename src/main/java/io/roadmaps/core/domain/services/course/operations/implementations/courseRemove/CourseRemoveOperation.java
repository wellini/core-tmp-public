package io.roadmaps.core.domain.services.course.operations.implementations.courseRemove;

import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.course.CourseRepository;
import io.roadmaps.core.domain.model.courseAffiliation.CourseAffiliationRepository;
import io.roadmaps.core.domain.model.leaf.LeafRepository;
import io.roadmaps.core.domain.model.module.ModuleRepository;
import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.roadmaps.core.domain.services.course.operations.Operation;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.EntityNotFoundException;

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
        Course course = courseRepository.findCourse(command.getCourseId()).orElseThrow(EntityNotFoundException::new);
        leafRepository.deleteAllByCourseId(course.getId());
        moduleRepository.deleteAllByCourseId(course.getId());
        courseAffiliationRepository.deleteAllByCourseId(course.getId());
        courseRepository.delete(course.getId());
        return ExplainedExecResult.empty();
    }
}
