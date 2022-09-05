package io.roadmaps.core.domain.services.course.operations.implementations.moduleRemove

import cc.roadmaps.core.domain.services.course.operations.implementations.moduleRemove.ModuleRemoveOperation
import cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands.ModuleRemoveCommandDto
import spock.lang.Specification

class ModuleRemoveOperationSpec extends Specification {

    private static UUID USER_ID = UUID.randomUUID()
    private static UUID COURSE_ID = UUID.randomUUID()

    def "Scenario: Remove module"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The course repository"
        def courseRepository = Mock(CourseRepository)

        and: "Target operation"
        def operation = new ModuleRemoveOperation(userService, courseAffiliationService, courseRepository)

        and: "Target course"
        def course = Mock(Course)
        course.getId() >> COURSE_ID

        and: "Removing command"
        def command = new ModuleRemoveCommandDto(UUID.randomUUID())

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(USER_ID, CourseAffiliationType.TEACHER, command)

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target course had been loaded"
        1 * courseRepository.findCourseByModuleId(command.getModuleId()) >> Optional.of(course)

        and: "The command had been dispatched to domain object"
        1 * course.removeModule(command.getModuleId())

        and: "Updated course had been saved"
        1 * courseRepository.save(_)
    }
}
