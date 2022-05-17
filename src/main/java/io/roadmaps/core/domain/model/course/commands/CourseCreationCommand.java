package io.roadmaps.core.domain.model.course.commands;

import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;

import java.util.UUID;

public interface CourseCreationCommand {

    String getTitle();

    CourseCoverTheme getCoverTheme();

    String getCoverUrl();
}
