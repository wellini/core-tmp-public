package io.roadmaps.core.domain.services.course.commands;

import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;

public interface EditPresentationServiceCommand {

    String getTitle();

    CourseCoverTheme getCoverTheme();
}
