package cc.roadmaps.core.domain.services.course.operations.implementations.createCourse;

import cc.roadmaps.core.domain.model.course.events.CourseCreationEvent;
import cc.roadmaps.core.domain.services.course.operations.commands.Command;

public interface CreateCourseCommand extends Command, CourseCreationEvent {

}
