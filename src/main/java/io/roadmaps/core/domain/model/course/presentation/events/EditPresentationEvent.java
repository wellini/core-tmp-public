package io.roadmaps.core.domain.model.course.presentation.events;

import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;

public interface EditPresentationEvent {

    String getTitle();

    CourseCoverTheme getCoverTheme();
}
