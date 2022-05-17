package io.roadmaps.core.domain.model.course.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class EnrollInCourseCommandImpl implements EnrollInCourseCommand {

    private final UUID userId;
}
