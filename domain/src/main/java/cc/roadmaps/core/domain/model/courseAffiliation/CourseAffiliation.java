package cc.roadmaps.core.domain.model.courseAffiliation;

import cc.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CourseAffiliation implements Serializable {

    @EqualsAndHashCode.Include
    private UUID courseId;

    @EqualsAndHashCode.Include
    private UUID userId;

    private CourseAffiliationType type;

    private CourseAffiliation(UUID courseId, UUID userId, CourseAffiliationType type) {
        if(type == CourseAffiliationType.GUEST) {
            throw new IllegalArgumentException("GUEST cannot be a type of affiliation because it is neutral value that means there is no any affiliation");
        } else {
            this.courseId = courseId;
            this.userId = userId;
            this.type = type;
        }
    }

    public static CourseAffiliation create(UUID courseId, UUID userId, CourseAffiliationType type) {
        return new CourseAffiliation(courseId, userId, type);
    }
}
