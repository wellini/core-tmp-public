package io.roadmaps.core.domain.services.course.operations.context.implementations;

import io.roadmaps.core.domain.common.id.IdExplanationFormatter;
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.utils.StringSubstitutor;

public class FormattedOperationExecutionContext extends AbstractOperationExecutionContext {

    private final IdExplanationFormatter formatter;

    public FormattedOperationExecutionContext(IdExplanationFormatter formatter, Long currentUserId, CourseAffiliationType currentUserAffiliationType, Long courseId, Long moduleId, Long leafId) {
        super(currentUserId, currentUserAffiliationType, courseId, moduleId, leafId);
        this.formatter = formatter;
    }

    @Override
    public String inflate(String template) {
        StringSubstitutor substitutor = StringSubstitutor.create();
        if (hasCurrentUserAffiliationType()) {
            substitutor.add("currentUserAffiliationType", currentUserAffiliationType);
        }
        if (hasCourseId()) {
            substitutor.add("courseId", formatter.format(courseId));
        }
        if (hasModuleId()) {
            substitutor.add("moduleId", formatter.format(moduleId));
        }
        if (hasLeafId()) {
            substitutor.add("leafId", formatter.format(leafId));
        }
        if (hasCurrentUserId()) {
            substitutor.add("currentUserId", formatter.format(currentUserId));
        }
        return substitutor.replace(template);
    }
}
