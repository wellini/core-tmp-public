package cc.roadmaps.core.domain.model.course.events;

import cc.roadmaps.core.domain.model.course.enums.CourseCoverTheme;

public interface CourseCreationEvent {

    String getTitle();

    CourseCoverTheme getCoverTheme();
}
