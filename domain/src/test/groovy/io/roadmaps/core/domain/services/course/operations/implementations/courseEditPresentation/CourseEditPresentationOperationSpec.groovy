package io.roadmaps.core.domain.services.course.operations.implementations.courseEditPresentation

import cc.roadmaps.core.domain.model.course.enums.CourseCoverTheme
import cc.roadmaps.core.domain.services.course.operations.implementations.courseEditPresentation.CourseEditPresentationOperation
import cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands.CourseEditPresentationCommandDto
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

class CourseEditPresentationOperationSpec extends Specification {

    private static CourseCoverTheme COURSE_COVER_THEME = CourseCoverTheme.WHITE
    private static UUID COURSE_ID = UUID.randomUUID()
    private static String COURSE_TITLE = RandomStringUtils.randomAlphabetic(100)

    def "Scenario: Edit and save presentation"() {
        given: "The user service"
        def userService = Mock(UserService)

        and: "The course affiliations service"
        def courseAffiliationService = Mock(CourseAffiliationService)

        and: "The course repository"
        def courseRepository = Mock(CourseRepository)

        and: "Current user that is being author of course"
        def user = TestFabric.identifiedUser()

        and: "An editing course"
        def course = Mock(Course.class)
        course.getId() >> COURSE_ID

        and: "Course edit presentation operation"
        def operation = new CourseEditPresentationOperation(userService, courseAffiliationService, courseRepository)

        and: "Command that has to be handled by operation"
        def command = new CourseEditPresentationCommandDto(COURSE_ID, COURSE_TITLE, COURSE_COVER_THEME)

        and: "An execution context given by superclass"
        def context = SimpleOperationExecutionContext.create(user.id, CourseAffiliationType.TEACHER, command)

        when: "Operation had been executed"
        def result = operation.doExecute(context, command)

        then: "Required course had been loaded from service"
        1 * courseRepository.findCourse(COURSE_ID) >> Optional.of(course)

        and: "The command had been dispatched to domain object"
        1 * course.editPresentation(command)

        and: "The course had been saved"
        1 * courseRepository.save(_)

        and: "The identifier of course had been returned"
        result.id == course.getId()
    }
}
