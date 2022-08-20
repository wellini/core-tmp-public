package io.roadmaps.core.integrations.web.rest.api.course.dtos;

import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.domain.model.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetStudentResponse {

    @EqualsAndHashCode.Include
    @Schema(required = true)
    private Long id;

    @Schema(required = true)
    private String fullname;

    @Schema(required = false)
    private String username;

    public static GetStudentResponse create(User user, CourseAffiliationType currentUserAffiliationType) {
        return switch(currentUserAffiliationType) {
            case TEACHER -> new GetStudentResponse(
                    user.getId(),
                    user.getFullname(),
                    user.getUsername()
            );
            default -> new GetStudentResponse(
                    user.getId(),
                    user.getFullname(),
                    null
            );
        };
    }
}
