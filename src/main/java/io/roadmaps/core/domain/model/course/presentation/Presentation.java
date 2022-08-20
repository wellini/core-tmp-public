package io.roadmaps.core.domain.model.course.presentation;

import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;
import io.roadmaps.core.domain.model.course.presentation.events.EditPresentationEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Presentation {

    private String title;

    private CourseCoverTheme coverTheme;

    private String coverUrl;

    public static Presentation create(String title, CourseCoverTheme coverTheme) {
        return new Presentation(
                title,
                coverTheme
        );
    }

    private Presentation(String title, CourseCoverTheme coverTheme) {
        this.title = title;
        this.coverTheme = coverTheme;
    }

    public void edit(EditPresentationEvent command) {
        title = command.getTitle();
        coverTheme = command.getCoverTheme();
    }
}
