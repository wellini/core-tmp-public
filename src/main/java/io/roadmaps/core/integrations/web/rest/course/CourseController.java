package io.roadmaps.core.integrations.web.rest.course;

import io.roadmaps.core.domain.model.course.Course;
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.services.CurrentUserIdProvider;
import io.roadmaps.core.domain.services.course.CourseService;
import io.roadmaps.core.exception.AuthException;
import io.roadmaps.core.integrations.web.rest.common.dtos.IdentifiedCommandExecResponse;
import io.roadmaps.core.integrations.web.rest.common.dtos.VoidCommandExecResponse;
import io.roadmaps.core.integrations.web.rest.course.dtos.GetCourseResponse;
import io.roadmaps.core.integrations.web.rest.course.dtos.commands.CourseCreationCommandRequest;
import io.roadmaps.core.integrations.web.rest.course.dtos.commands.CreateModuleCommandRequest;
import io.roadmaps.core.integrations.web.rest.course.dtos.commands.EditPresentationCommandRequest;
import io.roadmaps.core.integrations.web.rest.course.dtos.commands.RemoveModuleCommandRequest;
import io.roadmaps.core.integrations.web.rest.module.dtos.GetModuleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CurrentUserIdProvider currentUserIdProvider;

    @GetMapping("/api/courses/{id}")
    public GetCourseResponse getCourse(@PathVariable UUID id) {
        UUID currentUserId = currentUserIdProvider.getCurrentUserId().orElseThrow(AuthException::new);
        Course course = courseService.getCourse(id);
        return GetCourseResponse.create(course, course.getAffiliationType(currentUserId));
    }

    @GetMapping("/api/courses")
    public List<GetCourseResponse> getAllCourses(@RequestParam(required = false) CourseAffiliationType affiliationType) {
        UUID currentUserId = currentUserIdProvider.getCurrentUserId().orElseThrow(AuthException::new);
        if (Objects.isNull(affiliationType)) {
            return courseService.getAllCourses().stream()
                    .map(course -> GetCourseResponse.create(course, course.getAffiliationType(currentUserId)))
                    .collect(Collectors.toList());
        } else {
            return courseService.getAllCoursesByAffiliationType(affiliationType).stream()
                    .map(course -> GetCourseResponse.create(course, course.getAffiliationType(currentUserId)))
                    .collect(Collectors.toList());
        }
    }

    @GetMapping("/api/courses/{id}/modules")
    public List<GetModuleResponse> getModulesInCourse(@PathVariable UUID id) {
        List<Module> modules = courseService.getModulesInCourse(id);
        ArrayList<GetModuleResponse> response = new ArrayList<>();
        for (int i = 0; i < modules.size(); i++) {
            response.add(GetModuleResponse.create(modules.get(i), i));
        }
        return response;
    }

    @PostMapping("/api/courses/createCourse")
    public IdentifiedCommandExecResponse createCourse(@RequestBody CourseCreationCommandRequest command) {
        UUID courseId = courseService.createCourse(command);
        return IdentifiedCommandExecResponse.create(courseId, "Created Course with id {${id}}");
    }

    @PostMapping("/api/courses/{id}/editPresentation")
    public IdentifiedCommandExecResponse editPresentation(@PathVariable UUID id, @RequestBody EditPresentationCommandRequest command) {
        UUID courseId = courseService.editPresentation(id, command);
        return IdentifiedCommandExecResponse.create(courseId, "Edited presentation data in Course with id {${id}}");
    }

    @PostMapping("/api/courses/{id}/createModule")
    public IdentifiedCommandExecResponse createModule(@PathVariable UUID id, @RequestBody CreateModuleCommandRequest command) {
        UUID moduleId = courseService.createModule(id, command);
        return IdentifiedCommandExecResponse.create(moduleId, "Created Module with id {${}}");
    }

    @PostMapping("/api/courses/{id}/enrollInCourse")
    public IdentifiedCommandExecResponse enrollInCourse(@PathVariable UUID id) {
        courseService.enrollInCourse(id);
        return IdentifiedCommandExecResponse.create(id, "Enrolled in course with id {${id}}");
    }

    @PostMapping("/api/courses/{id}/removeModule")
    public IdentifiedCommandExecResponse removeModule(@PathVariable UUID id, @RequestBody RemoveModuleCommandRequest command) {
        UUID courseId = courseService.removeModule(id, command.getModuleId());
        return IdentifiedCommandExecResponse.create(courseId, "Removed Module from Course with id {${id}}");
    }

    @PostMapping("/api/courses/{id}/removeCourse")
    public VoidCommandExecResponse removeCourse(@PathVariable UUID id) {
        courseService.removeCourse(id);
        return VoidCommandExecResponse.create("Removed Course");
    }
}
