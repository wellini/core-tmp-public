package io.roadmaps.core.domain.model.courseAffiliation;

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CourseAffiliation {

    @EqualsAndHashCode.Include
    private UUID id;

    private UUID courseId;

    private UUID userId;

    private CourseAffiliationType type;

    private CourseAffiliation(UUID courseId, UUID userId, CourseAffiliationType type) {
        this.id = UUID.randomUUID();
        this.courseId = courseId;
        this.userId = userId;
        this.type = type;
    }

    public static CourseAffiliation create(UUID courseId, UUID userId, CourseAffiliationType type) {
        return new CourseAffiliation(courseId, userId, type);
    }
}
