package io.roadmaps.core.domain.model.course.presentation.commands;

import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;

public interface EditPresentationCommand {

    String getTitle();

    CourseCoverTheme getCoverTheme();

    String getCoverUrl();
}
