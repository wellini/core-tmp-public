package io.roadmaps.core.domain.services.course.operations.implementations.moduleCreate

import cc.roadmaps.core.domain.services.course.operations.implementations.moduleCreate.ModuleCreateOperation
import cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands.ModuleCreateCommandDto
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

class ModuleCreateOperationSpec extends Specification {

    private static UUID USER_ID = UUID.randomUUID()
    private static UUID COURSE_ID = UUID.randomUUID()
    private static UUID CREATED_MODULE_ID = UUID.randomUUID()

    def "Scenario: Create module"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The course repository"
        def courseRepository = Mock(CourseRepository)

        and: "Target operation"
        def operation = new ModuleCreateOperation(userService, courseAffiliationService, courseRepository, moduleIdSequenceGenerator)

        and: "Target course"
        def course = Mock(Course)
        course.getId() >> COURSE_ID

        and: "Creation command"
        def command = new ModuleCreateCommandDto(
                COURSE_ID,
                RandomStringUtils.randomAlphabetic(100),
                0
        )

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(USER_ID, CourseAffiliationType.TEACHER, command)

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target course had been loaded"
        1 * courseRepository.findCourse(command.getCourseId()) >> Optional.of(course)

        and: "The command had been dispatched to domain object"
        1 * course.addModule(command) >> CREATED_MODULE_ID

        and: "Created module had been saved"
        1 * courseRepository.save(_)
        context.getModuleId() == CREATED_MODULE_ID
        result.id == CREATED_MODULE_ID
    }
}
