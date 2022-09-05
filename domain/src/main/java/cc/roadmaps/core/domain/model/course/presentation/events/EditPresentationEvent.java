package cc.roadmaps.core.domain.model.course.presentation.events;

import cc.roadmaps.core.domain.model.course.enums.CourseCoverTheme;

public interface EditPresentationEvent {

    String getTitle();

    CourseCoverTheme getCoverTheme();
}
