package io.roadmaps.core.integrations.web.rest.course.dtos;

import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GetCourseResponse {

    @EqualsAndHashCode.Include
    @Schema(required = true)
    private UUID id;

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private UUID authorId;

    @Schema(required = true)
    private CourseCoverTheme coverTheme;

    @Schema(required = false)
    private String coverUrl;

    @Schema(required = true)
    private CourseAffiliationType affiliationType;

    @Schema(required = true)
    private String authorName;

    private GetCourseResponse(Course course, CourseAffiliationType affiliationWithCurrentUser) {
        id = course.getId();
        title = course.getPresentation().getTitle();
        authorId = course.getAuthor().getId();
        coverTheme = course.getPresentation().getCoverTheme();
        coverUrl = course.getPresentation().getCoverUrl();
        affiliationType = affiliationWithCurrentUser;
        authorName = course.getAuthor().getFullname();
    }

    public static GetCourseResponse create(Course course, CourseAffiliationType affiliationWithCurrentUser) {
        return new GetCourseResponse(course, affiliationWithCurrentUser);
    }
}
