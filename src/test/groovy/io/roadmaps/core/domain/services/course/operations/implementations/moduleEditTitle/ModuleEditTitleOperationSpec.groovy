package io.roadmaps.core.domain.services.course.operations.implementations.moduleEditTitle

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType
import io.roadmaps.core.domain.model.module.Module
import io.roadmaps.core.domain.model.module.ModuleRepository
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService
import io.roadmaps.core.integrations.web.rest.api.course.dtos.commands.ModuleEditTitleCommandDto
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

class ModuleEditTitleOperationSpec extends Specification {

    private static UUID USER_ID = UUID.randomUUID()
    private static UUID MODULE_ID = UUID.randomUUID()

    def "Scenario: Edit module title"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The modules repository"
        def moduleRepository = Mock(ModuleRepository)

        and: "Target operation"
        def operation = new ModuleEditTitleOperation(userService, courseAffiliationService, moduleRepository)

        and: "Target module"
        def module = Mock(Module)
        module.getId() >> MODULE_ID

        and: "Title editing command"
        def command = new ModuleEditTitleCommandDto(
                MODULE_ID,
                RandomStringUtils.randomAlphabetic(100)
        )

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(USER_ID, CourseAffiliationType.TEACHER, command)

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target module had been loaded"
        1 * moduleRepository.findModule(command.getModuleId()) >> Optional.of(module)

        and: "The command had been dispatched to domain object"
        1 * module.editTitle(command)

        and: "Updated module had been saved"
        1 * moduleRepository.save(_)
        result.id == MODULE_ID
    }
}
