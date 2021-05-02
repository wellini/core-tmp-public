package io.roadmaps.core.rest.courses;

import io.roadmaps.core.rest.courses.dto.CreateCourseDto;
import io.roadmaps.core.rest.courses.dto.UpdateCourseDto;
import io.roadmaps.core.rest.courses.dto.response.CreateCourseResponseDto;
import io.roadmaps.core.rest.courses.dto.response.GetAllCoursesResponseDto;
import io.roadmaps.core.rest.courses.dto.response.GetCourseResponseDto;
import io.roadmaps.core.rest.courses.dto.response.UpdateCourseResponseDto;
import io.roadmaps.core.swagger.ApiPageable;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseRestFacade restFacade;

    @GetMapping("/api/courses")
    @ApiPageable
    public Page<GetAllCoursesResponseDto> getAllCourses(@PageableDefault @Parameter(hidden = true) Pageable pageable) {
        return restFacade.getAllCourses(pageable);
    }

    @PostMapping("/api/courses")
    public CreateCourseResponseDto createCourse(@RequestBody CreateCourseDto dto) {
        return restFacade.createCourse(dto);
    }

    @GetMapping("/api/courses/{id}")
    public GetCourseResponseDto getCourse(@PathVariable UUID id) {
        return restFacade.getCourse(id);
    }

    @PatchMapping("/api/courses/{id}")
    public UpdateCourseResponseDto updateCourse(@PathVariable UUID id, @RequestBody UpdateCourseDto dto) {
        return restFacade.updateCourse(id, dto);
    }

    @DeleteMapping("/api/courses/{id}")
    public void deleteCourse(@PathVariable UUID id) {
        restFacade.deleteCourse(id);
    }
}
