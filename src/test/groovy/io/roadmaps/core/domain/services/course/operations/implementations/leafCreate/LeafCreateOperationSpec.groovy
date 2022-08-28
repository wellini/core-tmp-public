package io.roadmaps.core.domain.services.course.operations.implementations.leafCreate

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType
import io.roadmaps.core.domain.model.module.Module
import io.roadmaps.core.domain.model.module.ModuleRepository
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService
import io.roadmaps.core.integrations.web.rest.api.course.dtos.commands.LeafCreateCommandDto
import spock.lang.Specification

class LeafCreateOperationSpec extends Specification {

    private static UUID USER_ID = UUID.randomUUID()
    private static UUID LEAF_ID = UUID.randomUUID()

    def "Scenario: Create leaf"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The modules repository"
        def moduleRepository = Mock(ModuleRepository)

        and: "Leaf creation operation"
        def operation = new LeafCreateOperation(userService, courseAffiliationService, moduleRepository, leafIdSequenceGenerator)

        and: "Leaf creation command"
        def command = new LeafCreateCommandDto()

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(USER_ID, CourseAffiliationType.TEACHER, command)

        and: "Target module"
        def module = Mock(Module)

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target module had been loaded"
        1 * moduleRepository.findModule(command.getModuleId()) >> Optional.of(module)

        and: "The command had been dispatched to domain object"
        1 * module.addLeaf(_) >> LEAF_ID

        and: "Context had been updated"
        context.getLeafId() == LEAF_ID

        and: "Updated module had been updated"
        1 * moduleRepository.save(_)

        and: "The identifier of created leaf had been returned"
        result.id == LEAF_ID
    }
}
