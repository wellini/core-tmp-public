package io.roadmaps.core.domain.services.course.operations.context.implementations;

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.utils.StringSubstitutor;
import lombok.ToString;

@ToString
public class SimpleOperationExecutionContext extends AbstractOperationExecutionContext {

    public SimpleOperationExecutionContext(Long currentUserId, CourseAffiliationType currentUserAffiliationType, Long courseId, Long moduleId, Long leafId) {
        super(currentUserId, currentUserAffiliationType, courseId, moduleId, leafId);
    }

    @Override
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
