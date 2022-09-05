package cc.roadmaps.core.domain.services.course.operations.commands;


import cc.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import cc.roadmaps.core.domain.services.course.operations.context.OperationExecutionContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum CommandType {

    COURSE_CREATE("Create course", "Created course with id {${courseId}}"),
    COURSE_EDIT_PRESENTATION(
            "Edit presentation data in course with {${courseId}}",
            "Presentation in course with id {${courseId}} has been updated",
            CourseAffiliationType.TEACHER
    ),
    COURSE_REMOVE(
            "Remove course with id {${courseId}}",
            "Course with id {${courseId}} has been removed",
            CourseAffiliationType.TEACHER
    ),
    ENROLL_IN_COURSE(
            "Enroll user with id {${currentUserId}} in course with id {${courseId}}",
            "User with id {${currentUserId}} enrolled in course with id {${courseId}}",
            CourseAffiliationType.GUEST
    ),
    MODULE_CREATE(
            "Create module in course with id {${courseId}}",
            "Module with id {${moduleId}} has been created in course with id {${courseId}}",
            CourseAffiliationType.TEACHER
    ),
    MODULE_EDIT_TITLE(
            "Edit title in module with id {${moduleId}}",
            "Title in module with id {${moduleId}} has been updated",
            CourseAffiliationType.TEACHER
    ),
    MODULE_REMOVE(
            "Remove module with id {${moduleId}}",
            "Module with id {${moduleId}} has been removed from course with id {${courseId}}",
            CourseAffiliationType.TEACHER
    ),
    MODULE_MOVE(
            "Move module with id {${moduleId}}",
            "Module with id {${moduleId}} has been moved",
            CourseAffiliationType.TEACHER
    ),
    LEAF_CREATE(
            "Create leaf in module with id {${moduleId}}",
            "Leaf with id {${leafId}} has been created in module with id {${moduleId}}",
            CourseAffiliationType.TEACHER
    ),
    LEAF_REMOVE(
            "Remove leaf with id {${leafId}}",
            "Leaf with id {${leafId}} has been removed from module with id {${moduleId}}",
            CourseAffiliationType.TEACHER
    ),
    LEAF_EDIT_TITLE(
            "Edit title in leaf with id {${leafId}}",
            "Title in leaf with id {${leafId}} has been updated",
            CourseAffiliationType.TEACHER
    ),
    LEAF_UPDATE_TEXT(
            "Update text in leaf with id {${leafId}}",
            "Text in leaf with id {${leafId}} has been updated",
            CourseAffiliationType.TEACHER
    ),
    LEAF_MOVE(
            "Move leaf with id {${leafId}}",
            "Leaf with id {${leafId}} has been moved",
            CourseAffiliationType.TEACHER
    );

    private final Set<CourseAffiliationType> allowedFor;

    private final String toDoMessageTemplate;
    private final String completedMessageTemplate;

    CommandType(String toDoMessageTemplate, String completedMessageTemplate, CourseAffiliationType... allowedFor) {
        this.allowedFor = new HashSet<>(Arrays.asList(allowedFor));
        this.toDoMessageTemplate = toDoMessageTemplate;
        this.completedMessageTemplate = completedMessageTemplate;

    }

    public boolean isAllowedFor(CourseAffiliationType affiliationType) {
        return allowedFor.contains(affiliationType);
    }

    public boolean hasRestrictedAccess() {
        return !allowedFor.isEmpty();
    }

    public String getToDoMessage(OperationExecutionContext context) {
        return context.inflate(toDoMessageTemplate);
    }

    public String getCompletedMessage(OperationExecutionContext context) {
        return context.inflate(completedMessageTemplate);
    }
}
