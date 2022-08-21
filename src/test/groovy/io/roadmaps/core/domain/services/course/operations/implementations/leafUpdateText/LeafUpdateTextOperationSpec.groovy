package io.roadmaps.core.domain.services.course.operations.implementations.leafUpdateText

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType
import io.roadmaps.core.domain.model.leaf.LeafRepository
import io.roadmaps.core.domain.model.leaf.TextLeaf
import io.roadmaps.core.domain.model.leaf.enums.LeafType
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService
import io.roadmaps.core.integrations.web.rest.course.dtos.commands.LeafUpdateTextCommandDto
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

class LeafUpdateTextOperationSpec extends Specification {

    private static UUID USER_ID = UUID.randomUUID()
    private static UUID LEAF_ID = UUID.randomUUID()

    def "Scenario: Update text leaf"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The leaf repository"
        def leafRepository = Mock(LeafRepository)

        and: "Target operation"
        def operation = new LeafUpdateTextOperation(userService, courseAffiliationService, leafRepository)

        and: "Target leaf"
        def leaf = Mock(TextLeaf)
        leaf.getId() >> LEAF_ID

        and: "Text updating command"
        def command = new LeafUpdateTextCommandDto(
                LEAF_ID,
                RandomStringUtils.randomAlphabetic(100)
        )

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(USER_ID, CourseAffiliationType.TEACHER, command)

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target leaf had been loaded"
        1 * leafRepository.findLeafByIdAndType(LEAF_ID, LeafType.TEXT) >> Optional.of(leaf)

        and: "The command had been dispatched to domain object"
        1 * leaf.updateText(command)

        and: "Leaf with updated text had been saved"
        1 * leafRepository.save(_)

        and: "The identifier of created leaf had been returned"
        result.id == LEAF_ID
    }
}
