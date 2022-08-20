package io.roadmaps.core.domain.model.course.affiliationsregistry;

import io.roadmaps.core.domain.model.courseAffiliation.CourseAffiliation;
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
public class AffiliationsRegistry {

    private Long courseId;

    @Getter
    private Set<CourseAffiliation> courseAffiliations = new HashSet<>();

    private Map<Long, CourseAffiliationType> indexByUsers = new HashMap();

    public static AffiliationsRegistry init(Long courseId) {
        AffiliationsRegistry affiliationsRegistry = new AffiliationsRegistry();
        affiliationsRegistry.courseId = courseId;
        return affiliationsRegistry;
    }

    public void setCourseAffiliations(Set<CourseAffiliation> courseAffiliations) {
        this.courseAffiliations = courseAffiliations;
        refreshIndex();
    }

    public CourseAffiliationType getAffiliationType(Long userId) {
        return indexByUsers.get(userId);
    }

    public void addAffiliation(Long userId, CourseAffiliationType type) {
        courseAffiliations.add(CourseAffiliation.create(courseId, userId, type));
        refreshIndex();
    }

    private void refreshIndex() {
        indexByUsers = new HashMap<>();
        courseAffiliations.forEach(ca -> indexByUsers.put(ca.getUserId(), ca.getType()));
    }
}
