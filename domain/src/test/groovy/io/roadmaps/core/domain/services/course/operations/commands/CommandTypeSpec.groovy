package io.roadmaps.core.domain.services.course.operations.commands

import cc.roadmaps.core.domain.services.course.operations.commands.CommandType
import spock.lang.Specification

class CommandTypeSpec extends Specification {

    private final CommandType COMMAND_ALLOWED_FOR_ALL = CommandType.COURSE_CREATE
    private final CommandType COMMAND_ALLOWED_FOR_TEACHER = CommandType.LEAF_MOVE

    private final String MESSAGE = "MESSAGE"

    def "Scenario: CommandType checks if command has restricted access"() {
        when: "Command with restricted access"
        def commandWithRestrictedAccess =  COMMAND_ALLOWED_FOR_TEACHER

        and: "Command without restricted access"
        def commandWithoutRestrictedAccess =  COMMAND_ALLOWED_FOR_ALL

        then: "Command with restricted access actually has it"
        commandWithRestrictedAccess.hasRestrictedAccess()

        and: "Command without restricted access actually hasn't it"
        !commandWithoutRestrictedAccess.hasRestrictedAccess()
    }

    def "Scenario: CommandType checks access to execution"() {
        when: "Command allowed for teacher"
        def commandAllowedForTeacher = COMMAND_ALLOWED_FOR_TEACHER

        then: "Not allowed for student"
        !commandAllowedForTeacher.isAllowedFor(CourseAffiliationType.STUDENT)

        and: "Is allowed for teacher"
        commandAllowedForTeacher.isAllowedFor(CourseAffiliationType.TEACHER)
    }

    def "Scenario: If there isn't restricted access then command should actually hasn't it but be not allowed for anyone"() {
        when: "Command without restricted access"
        def commandWithoutRestrictedAccess = COMMAND_ALLOWED_FOR_ALL

        then: "Command without restricted access actually hasn't it"
        !commandWithoutRestrictedAccess.hasRestrictedAccess()

        and: "It isn't allowed for anyone"
        !commandWithoutRestrictedAccess.isAllowedFor(CourseAffiliationType.GUEST)
        !commandWithoutRestrictedAccess.isAllowedFor(CourseAffiliationType.STUDENT)
        !commandWithoutRestrictedAccess.isAllowedFor(CourseAffiliationType.TEACHER)
    }

    def "Scenario: CommandType inflates templates with context values"() {
        given: "Context of operation"
        def context = Mock(SimpleOperationExecutionContext)

        when: "To do message template had been inflated"
        def toDoMessage = CommandType.COURSE_CREATE.getToDoMessage(context)

        and: "Completed message template had been inflated"
        def completedMessage = CommandType.COURSE_CREATE.getCompletedMessage(context)

        then: "Context had been called to inflate two templates"
        2 * context.inflate(_) >> MESSAGE

        and: "Inflated message had been returned"
        toDoMessage == MESSAGE
        completedMessage == MESSAGE
    }
}
