package io.roadmaps.core.domain.services.course.operations.implementations.leafEditTitle

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType
import io.roadmaps.core.domain.model.leaf.LeafRepository
import io.roadmaps.core.domain.model.leaf.TextLeaf
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService
import io.roadmaps.core.integrations.web.rest.api.course.dtos.commands.LeafEditTitleCommandDto
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

class LeafEditTitleOperationSpec extends Specification {

    private static UUID USER_ID = UUID.randomUUID()

    def "Scenario: Edit leaf title"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The leaf repository"
        def leafRepository = Mock(LeafRepository)

        and: "Title editing title"
        def operation = new LeafEditTitleOperation(userService, courseAffiliationService, leafRepository)

        and: "Title editing command"
        def command = new LeafEditTitleCommandDto(UUID.randomUUID(), RandomStringUtils.randomAlphabetic(100))

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(USER_ID, CourseAffiliationType.TEACHER, command)

        and: "Target leaf"
        def leaf = Mock(TextLeaf)
        leaf.getId() >> UUID.randomUUID()

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target leaf had been loaded"
        1 * leafRepository.findLeaf(command.getLeafId()) >> Optional.of(leaf)

        and: "The command had been dispatched to domain object"
        1 * leaf.editTitle(command)

        and: "Updated leaf had been updated"
        1 * leafRepository.save(_)

        and: "The identifier of created leaf had been returned"
        Objects.nonNull(result.id)
    }
}
