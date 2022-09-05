package cc.roadmaps.core.domain.services.course.operations.context;

import cc.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import cc.roadmaps.core.domain.services.course.operations.commands.Command;
import cc.roadmaps.core.domain.utils.StringSubstitutor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

public class OperationExecutionContext {

    @Getter
    @Setter
    protected CourseAffiliationType currentUserAffiliationType;

    @Getter
    @Setter
    protected UUID courseId;

    @Getter
    @Setter
    protected UUID moduleId;

    @Getter
    @Setter
    protected UUID leafId;

    @Getter
    protected UUID currentUserId;

    private OperationExecutionContext(UUID currentUserId, CourseAffiliationType currentUserAffiliationType, UUID courseId, UUID moduleId, UUID leafId) {
        this.courseId = courseId;
        this.moduleId = moduleId;
        this.leafId = leafId;
        this.currentUserId = currentUserId;
        this.currentUserAffiliationType = currentUserAffiliationType;
    }

    public static <T extends Command> OperationExecutionContext create(UUID currentUserId, T command) {
        return new OperationExecutionContext(
                currentUserId,
                null,
                command.getCourseId(),
                command.getModuleId(),
                command.getLeafId()
        );
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

    public String inflate(String template) {
        StringSubstitutor substitutor = StringSubstitutor.create();
        if (hasCurrentUserAffiliationType()) {
            substitutor.add("currentUserAffiliationType", currentUserAffiliationType);
        }
        if (hasCourseId()) {
            substitutor.add("courseId", courseId);
        }
        if (hasModuleId()) {
            substitutor.add("moduleId", moduleId);
        }
        if (hasLeafId()) {
            substitutor.add("leafId", leafId);
        }
        if (hasCurrentUserId()) {
            substitutor.add("currentUserId", currentUserId);
        }
        return substitutor.replace(template);
    }
}
