package io.roadmaps.core.domain.services.course.operations.implementations.createCourse;

import io.roadmaps.core.domain.model.course.events.CourseCreationEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

public interface CreateCourseCommand extends Command, CourseCreationEvent {

}
