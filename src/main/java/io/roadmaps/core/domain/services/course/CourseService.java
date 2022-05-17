package io.roadmaps.core.domain.services.course;

import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.course.commands.CourseCreationCommand;
import io.roadmaps.core.domain.model.course.commands.ModuleCreationCommand;
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.services.course.commands.EditPresentationServiceCommand;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    Course getCourse(UUID id);

    List<Course> getAllCourses();

    List<Course> getAllCoursesByAffiliationType(CourseAffiliationType affiliationType);

    List<Module> getModulesInCourse(UUID id);

    UUID createCourse(CourseCreationCommand creationCommand);

    UUID editPresentation(UUID id, EditPresentationServiceCommand editCourseCoverCommand);

    UUID createModule(UUID id, ModuleCreationCommand moduleCreationCommand);

    UUID enrollInCourse(UUID id);

    UUID removeModule(UUID courseId, UUID moduleId);

    void removeCourse(UUID id);
}
