package io.roadmaps.core.domain.services.course.operations


import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType
import io.roadmaps.core.domain.model.user.User
import io.roadmaps.core.domain.services.course.operations.commands.CommandType
import io.roadmaps.core.domain.services.course.operations.utils.TestCommand
import io.roadmaps.core.domain.services.course.operations.utils.TestFabric
import io.roadmaps.core.domain.services.course.operations.utils.TestOperation
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService
import io.roadmaps.core.exception.NotEnoughPermissionsException
import spock.lang.Specification

class OperationSpec extends Specification {

    private final CommandType COMMAND_TYPE_ALLOWED_FOR_TEACHER = CommandType.LEAF_MOVE
    private final CommandType COMMAND_TYPE_ALLOWED_FOR_ALL = CommandType.COURSE_CREATE

    def "Scenario: Operation executes if affiliation is presented among allowed ones" () {
        given: "The user service"
        UserService userService = Mock(UserService)

        and: "The course affiliations service"
        CourseAffiliationService courseAffiliationService = Mock(CourseAffiliationService)

        and: "Some service with target method to call from operation if it's being executed"
        TestOperation.ControlActionService controlActionService = Mock(TestOperation.ControlActionService)

        and: "Test operation of some type that has restricted access to execution and calls control action in implemented Operation::doExecute method"
        TestOperation operation = new TestOperation(userService, courseAffiliationService, controlActionService)
        operation.setCommandType(COMMAND_TYPE_ALLOWED_FOR_TEACHER)

        and: "Test command of same type"
        TestCommand command = TestFabric.validCommand(COMMAND_TYPE_ALLOWED_FOR_TEACHER)

        and: "Current user with his id"
        User currentUser = TestFabric.identifiedUser()

        when: "Operation had been executed"
        operation.execute(command)

        then: "The current user had been loaded from service"
        1 * userService.getCurrentUser() >> currentUser

        and: "Affiliation to the course had been resolved by course affiliation service"
        1 * courseAffiliationService.safeResolveAffiliation(currentUser.getId(), command) >> CourseAffiliationType.TEACHER

        and: "Control action from template method had been done"
        1 * controlActionService.doControlAction()
    }

    def "Scenario: Operation DOES NOT execute if affiliation is NOT presented among allowed ones"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "Some service with target method to call from operation if it's being executed"
        TestOperation.ControlActionService controlActionService = Mock(TestOperation.ControlActionService)

        and: "Test operation of some type that has restricted access to execution and calls control action in implemented Operation::doExecute method"
        TestOperation operation = new TestOperation(userService, courseAffiliationService, controlActionService)
        operation.setCommandType(COMMAND_TYPE_ALLOWED_FOR_TEACHER)

        and: "Test command of same type"
        def command = TestFabric.validCommand(COMMAND_TYPE_ALLOWED_FOR_TEACHER)

        and: "Current user with his id"
        def currentUser = TestFabric.identifiedUser()

        when: "Operation had been executed"
        operation.execute(command)

        then: "The current user had been loaded from service"
        1 * userService.getCurrentUser() >> currentUser

        and: "Affiliation to the course had been resolved by course affiliation service"
        1 * courseAffiliationService.safeResolveAffiliation(currentUser.getId(), command) >> CourseAffiliationType.STUDENT

        and: "Control action from template method had NOT been done"
        0 * controlActionService.doControlAction()

        and: "Exception because of not enough permissions had been thrown"
        thrown(NotEnoughPermissionsException.class)
    }

    def "Scenario: Operation executes if there are no allowedFor configuration in command type"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "Some service with target method to call from operation if it's being executed"
        TestOperation.ControlActionService controlActionService = Mock(TestOperation.ControlActionService)

        and: "Test operation of some type that is allowed for all and calls control action in implemented Operation::doExecute method"
        TestOperation operation = new TestOperation(userService, courseAffiliationService, controlActionService)
        operation.setCommandType(COMMAND_TYPE_ALLOWED_FOR_ALL)

        and: "Test command of same type"
        def command = TestFabric.validCommand(COMMAND_TYPE_ALLOWED_FOR_ALL)

        and: "Current user with his id"
        def currentUser = TestFabric.identifiedUser()

        when: "Operation had been executed"
        operation.execute(command)

        then: "The current user had been loaded from service"
        1 * userService.getCurrentUser() >> currentUser

        and: "Affiliation service had not been called"
        0 * courseAffiliationService.safeResolveAffiliation(currentUser.getId(), command)

        and: "Control action from template method had been done"
        1 * controlActionService.doControlAction()
    }
}
