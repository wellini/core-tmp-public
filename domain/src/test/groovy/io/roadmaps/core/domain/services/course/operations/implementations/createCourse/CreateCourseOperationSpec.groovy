package io.roadmaps.core.domain.services.course.operations.implementations.createCourse

import cc.roadmaps.core.domain.model.course.enums.CourseCoverTheme
import cc.roadmaps.core.domain.services.course.operations.implementations.createCourse.CreateCourseOperation
import cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands.CreateCourseCommandDto
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

class CreateCourseOperationSpec extends Specification {

    private static String COURSE_TITLE = RandomStringUtils.randomAlphabetic(100)
    private static CourseCoverTheme COURSE_COVER_THEME = CourseCoverTheme.BLACK

    def "Scenario: Create course"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The course repository"
        def courseRepository = Mock(CourseRepository)

        and: "Course creation operation"
        def operation = new CreateCourseOperation(userService, courseAffiliationService, courseRepository, courseIdSequenceGenerator)

        and: "Course creation command"
        def command = new CreateCourseCommandDto(COURSE_TITLE, COURSE_COVER_THEME)

        and: "An execution context given by superclass"
        def context = Mock(SimpleOperationExecutionContext)

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Current user had been loaded"
        1 * userService.getCurrentUser() >> TestFabric.identifiedUser()

        and: "Id of created course had been set"
        1 * context.setCourseId(_)

        and: "Created course had been saved"
        1 * courseRepository.save(_)

        and: "Id of created course had been returned"
        Objects.nonNull(result.id)
    }
}
