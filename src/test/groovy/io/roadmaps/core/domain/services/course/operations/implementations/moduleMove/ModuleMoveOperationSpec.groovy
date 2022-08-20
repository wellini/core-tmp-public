package io.roadmaps.core.domain.services.course.operations.implementations.moduleMove

import io.roadmaps.core.domain.model.course.Course
import io.roadmaps.core.domain.model.course.CourseRepository
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType
import io.roadmaps.core.domain.services.course.operations.context.implementations.SimpleOperationExecutionContext
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService
import io.roadmaps.core.integrations.web.rest.api.course.dtos.commands.ModuleMoveCommandDto
import spock.lang.Specification

class ModuleMoveOperationSpec extends Specification {

    private static UUID USER_ID = UUID.randomUUID()
    private static UUID COURSE_ID = UUID.randomUUID()

    def "Scenario: Move module"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The course repository"
        def courseRepository = Mock(CourseRepository)

        and: "Target operation"
        def operation = new ModuleMoveOperation(userService, courseAffiliationService, courseRepository)

        and: "Target course"
        def course = Mock(Course)
        course.getId() >> COURSE_ID

        and: "Moving command"
        def command = new ModuleMoveCommandDto(UUID.randomUUID(), 0)

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(USER_ID, CourseAffiliationType.TEACHER, command)

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target course had been loaded"
        1 * courseRepository.findCourseByModuleId(command.getModuleId()) >> Optional.of(course)

        and: "The command had been dispatched to domain object"
        1 * course.moveModule(command.getModuleId(), command)

        and: "Updated course had been saved"
        1 * courseRepository.save(_)
        result.id == command.getModuleId()
    }
}
