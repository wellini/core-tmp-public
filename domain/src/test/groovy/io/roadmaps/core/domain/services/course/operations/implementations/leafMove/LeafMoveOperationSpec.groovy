package io.roadmaps.core.domain.services.course.operations.implementations.leafMove

import cc.roadmaps.core.domain.services.course.operations.implementations.leafMove.LeafMoveOperation
import cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands.LeafMoveCommandDto
import spock.lang.Specification

class LeafMoveOperationSpec extends Specification {

    private static UUID USER_ID = UUID.randomUUID()
    private static UUID COURSE_ID = UUID.randomUUID()

    def "Scenario: Move leaf"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The course repository"
        def courseRepository = Mock(CourseRepository)

        and: "The operation"
        def operation = new LeafMoveOperation(userService, courseAffiliationService, courseRepository)

        and: "Target course mock"
        def course = Mock(Course.class)
        course.getId() >> COURSE_ID

        and: "Leaf moving command"
        def command = new LeafMoveCommandDto(
                UUID.randomUUID(),
                UUID.randomUUID(),
                0
        )

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(USER_ID, CourseAffiliationType.TEACHER, command)

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target course had been loaded"
        1 * courseRepository.findCourseByLeafId(command.getLeafId()) >> Optional.of(course)

        and: "Call domain method"
        1 * course.moveLeaf(command.getLeafId(), _)

        and: "Course had been saved"
        1 * courseRepository.save(_)

        and: "The identifier of modified leaf had been returned"
        result.id == command.getLeafId()
    }
}
