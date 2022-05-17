package io.roadmaps.core.domain.services.course.implementations;

import io.roadmaps.core.domain.model.course.presentation.commands.EditPresentationCommand;
import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EditPresentationCommandWithoutCoverUrl implements EditPresentationCommand {

    private String title;

    private CourseCoverTheme coverTheme;

    private String coverUrl;

    public static EditPresentationCommandWithoutCoverUrl create(String title, CourseCoverTheme coverTheme) {
        return new EditPresentationCommandWithoutCoverUrl(title, coverTheme, null);
    }
}
