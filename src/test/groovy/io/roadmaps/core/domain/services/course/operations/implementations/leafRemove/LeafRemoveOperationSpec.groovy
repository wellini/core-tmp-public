package io.roadmaps.core.domain.services.course.operations.implementations.leafRemove

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType
import io.roadmaps.core.domain.model.module.Module
import io.roadmaps.core.domain.model.module.ModuleRepository
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService
import io.roadmaps.core.integrations.web.rest.api.course.dtos.commands.LeafRemoveCommandDto
import spock.lang.Specification

class LeafRemoveOperationSpec extends Specification {

    private static UUID USER_ID = UUID.randomUUID()
    private static UUID MODULE_ID = UUID.randomUUID()

    def "Scenario: Remove leaf"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The modules repository"
        def moduleRepository = Mock(ModuleRepository)

        and: "Target operation"
        def operation = new LeafRemoveOperation(userService, courseAffiliationService, moduleRepository)

        and: "Leaf removing command"
        def command = new LeafRemoveCommandDto(UUID.randomUUID())

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(USER_ID, CourseAffiliationType.TEACHER, command)

        and: "Target module"
        def module = Mock(Module)
        module.getId() >> MODULE_ID

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target leaf had been loaded"
        1 * moduleRepository.findModuleByLeafId(command.getLeafId()) >> Optional.of(module)

        and: "The command had been dispatched to domain object"
        1 * module.removeLeaf(command.getLeafId())

        and: "Module had been saved"
        1 * moduleRepository.save(_)

        and: "Context had been updated"
        context.getModuleId() == MODULE_ID
    }
}
