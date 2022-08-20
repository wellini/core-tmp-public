package io.roadmaps.core.domain.services.course.operations.implementations.courseRemove

import io.roadmaps.core.domain.model.course.Course
import io.roadmaps.core.domain.model.course.CourseRepository
import io.roadmaps.core.domain.model.courseAffiliation.CourseAffiliationRepository
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType
import io.roadmaps.core.domain.model.leaf.LeafRepository
import io.roadmaps.core.domain.model.module.ModuleRepository
import io.roadmaps.core.domain.services.course.operations.context.implementations.SimpleOperationExecutionContext
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService
import io.roadmaps.core.integrations.web.rest.api.course.dtos.commands.CourseRemoveCommandDto
import spock.lang.Specification

class CourseRemoveOperationSpec extends Specification {

    private static UUID COURSE_ID = UUID.randomUUID()

    def "Scenario: Remove course"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The leaf repository"
        def leafRepository = Mock(LeafRepository)

        and: "The module repository"
        def moduleRepository = Mock(ModuleRepository)

        and: "The affiliations repository"
        def courseAffiliationRepository = Mock(CourseAffiliationRepository)

        and: "The course repository"
        def courseRepository = Mock(CourseRepository)

        and: "Course removal operation"
        def operation = new CourseRemoveOperation(userService, courseAffiliationService, leafRepository, moduleRepository, courseAffiliationRepository, courseRepository)

        and: "Course removal command"
        def command = new CourseRemoveCommandDto(COURSE_ID)

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(UUID.randomUUID(), CourseAffiliationType.TEACHER, command)

        and: "Removing course"
        def course = Mock(Course.class)
        course.getId() >> COURSE_ID

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target course had been loaded"
        1 * courseRepository.findCourse(_) >> Optional.of(course)

        and: "All leaves had been removed"
        1 * leafRepository.deleteAllByCourseId(course.getId())

        and: "All modules had been removed"
        1 * moduleRepository.deleteAllByCourseId(course.getId())

        and: "All affiliations had been removed"
        1 * courseAffiliationRepository.deleteAllByCourseId(course.getId())

        and: "Course had been removed"
        1 * courseRepository.delete(course.getId())

        and: "Returned no id"
        Objects.isNull(result.id)
    }
}
