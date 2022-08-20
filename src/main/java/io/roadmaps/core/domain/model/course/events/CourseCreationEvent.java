package io.roadmaps.core.domain.model.course.events;

import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;

public interface CourseCreationEvent {

    String getTitle();

    CourseCoverTheme getCoverTheme();
}
