package io.roadmaps.core.domain.model.course.presentation;

import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;
import io.roadmaps.core.domain.model.course.presentation.commands.EditPresentationCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Presentation {

    private String title;

    private CourseCoverTheme coverTheme;

    private String coverUrl;

    public static Presentation create(String title, CourseCoverTheme coverTheme, String coverUrl) {
        return new Presentation(
                title,
                coverTheme,
                coverUrl
        );
    }

    public void edit(EditPresentationCommand command) {
        title = command.getTitle();
        coverTheme = command.getCoverTheme();
        coverUrl = command.getCoverUrl();
    }
}
