package cc.roadmaps.core.service.integrations.web.rest.api.course;

import cc.roadmaps.core.domain.model.course.Course;
import cc.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import cc.roadmaps.core.domain.model.module.Module;
import cc.roadmaps.core.domain.model.user.User;
import cc.roadmaps.core.domain.services.CurrentUserIdProvider;
import cc.roadmaps.core.domain.services.course.CourseService;
import cc.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import cc.roadmaps.core.domain.services.course.operations.OperationsService;
import cc.roadmaps.core.service.integrations.web.rest.api.auth.exceptions.UnauthorizedException;
import cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands.AbstractCommandDto;
import cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.responses.GetCourseResponse;
import cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.responses.GetStudentResponse;
import cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.responses.commandExecution.AbstractCommandExecResponse;
import cc.roadmaps.core.service.integrations.web.rest.api.module.dtos.GetModuleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

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
    private final OperationsService operationsService;

    @GetMapping("/api/courses/{id}")
    public GetCourseResponse getCourse(@PathVariable UUID id) {
        UUID currentUserId = currentUserIdProvider.getCurrentUserId().orElseThrow(UnauthorizedException::new);
        Course course = courseService.getCourse(id);
        return GetCourseResponse.create(course, course.getAffiliationType(currentUserId));
    }

    @GetMapping("/api/courses")
    public List<GetCourseResponse> getAllCourses(@RequestParam(required = false) CourseAffiliationType affiliationType) {
        UUID currentUserId = currentUserIdProvider.getCurrentUserId().orElseThrow(UnauthorizedException::new);
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

    @GetMapping("/api/courses/{id}/students")
    public List<GetStudentResponse> getStudentsInCourse(@PathVariable UUID id) {
        Course course = courseService.getCourse(id);
        CourseAffiliationType affiliationWithCurrentUser = course.getAffiliationType(currentUserIdProvider.getCurrentUserId()
                .orElseThrow(() -> new NotFoundException("Course not found")));
        List<User> students = courseService.getStudentsInCourse(id);
        return students.stream()
                .map(user -> GetStudentResponse.create(user, affiliationWithCurrentUser))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/courses/execute")
    public <T extends AbstractCommandDto> AbstractCommandExecResponse execute(@RequestBody T command) {
        command.validate();
        ExplainedExecResult execResult = operationsService.execute(command);
        return AbstractCommandExecResponse.create(execResult);
    }
}
