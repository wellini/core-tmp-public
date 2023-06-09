package io.roadmaps.core.domain.services.course.operations

import cc.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext
import spock.lang.Specification

class OperationExecutionContextSpec extends Specification {

    def "Scenario: Operation context inflates template"() {
        given: "Operation context with all fulfilled fields"
        def context = new OperationExecutionContext()
        context.currentUserId = UUID.randomUUID()
        context.currentUserAffiliationType = CourseAffiliationType.GUEST
        context.courseId = UUID.randomUUID()
        context.moduleId = UUID.randomUUID()
        context.leafId = UUID.randomUUID()

        when: "Template had been inflated"
        def inflatedTemplate = context.inflate('${currentUserAffiliationType}${courseId}${moduleId}${leafId}${currentUserId}')

        then: "All placeholders had been changed to its values"
        inflatedTemplate == "${context.getCurrentUserAffiliationType()}${context.getCourseId()}${context.getModuleId()}${context.getLeafId()}${context.getCurrentUserId()}"
    }
}
