package io.roadmaps.core.domain.services.course.operations.context.implementations;

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public abstract class AbstractOperationExecutionContext {

    @Getter
    @Setter
    protected CourseAffiliationType currentUserAffiliationType;

    @Getter
    @Setter
    protected Long courseId;

    @Getter
    @Setter
    protected Long moduleId;

    @Getter
    @Setter
    protected Long leafId;

    @Getter
    protected Long currentUserId;

    protected AbstractOperationExecutionContext(Long currentUserId, CourseAffiliationType currentUserAffiliationType, Long courseId, Long moduleId, Long leafId) {
        this.courseId = courseId;
        this.moduleId = moduleId;
        this.leafId = leafId;
        this.currentUserId = currentUserId;
        this.currentUserAffiliationType = currentUserAffiliationType;
    }

    public boolean hasCurrentUserAffiliationType() {
        return Objects.nonNull(currentUserAffiliationType);
    }

    public boolean hasCourseId() {
        return Objects.nonNull(courseId);
    }

    public boolean hasModuleId() {
        return Objects.nonNull(moduleId);
    }

    public boolean hasLeafId() {
        return Objects.nonNull(leafId);
    }

    public boolean hasCurrentUserId() {
        return Objects.nonNull(currentUserId);
    }

    public abstract String inflate(String template);
}
