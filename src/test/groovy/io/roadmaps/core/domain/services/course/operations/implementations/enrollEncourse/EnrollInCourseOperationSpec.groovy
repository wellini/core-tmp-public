package io.roadmaps.core.domain.services.course.operations.implementations.enrollEncourse

import io.roadmaps.core.domain.model.course.Course
import io.roadmaps.core.domain.model.course.CourseRepository
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService
import io.roadmaps.core.domain.services.user.UserService
import io.roadmaps.core.integrations.web.rest.api.course.dtos.commands.EnrollInCourseCommandDto
import spock.lang.Specification

class EnrollInCourseOperationSpec extends Specification {

    private static UUID COURSE_ID = UUID.randomUUID()
    private static UUID USER_ID = UUID.randomUUID()

    def "Scenario: Enroll in course"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The course repository"
        def courseRepository = Mock(CourseRepository)

        and: "Enrolling operation"
        def operation = new EnrollInCourseOperation(userService, courseAffiliationService, courseRepository)

        and: "Enrolling command"
        def command = new EnrollInCourseCommandDto(COURSE_ID)

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(USER_ID, CourseAffiliationType.GUEST, command)

        and: "Target course"
        def course = Mock(Course)
        course.getAffiliationType(USER_ID) >> CourseAffiliationType.STUDENT

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Target course had been loaded"
        1 * courseRepository.findCourse(COURSE_ID) >> Optional.of(course)

        and: "The command had been dispatched to domain object"
        1 * course.enrollInCourse(_)

        and: "Created course had been saved"
        1 * courseRepository.save(course)

        and: "Context had been updated"
        context.getCurrentUserAffiliationType() == CourseAffiliationType.STUDENT

        and: "The identifier of course had been returned"
        result.id == course.getId()
    }
}
