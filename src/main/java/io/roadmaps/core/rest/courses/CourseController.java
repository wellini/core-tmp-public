package io.roadmaps.core.rest.courses;

import io.roadmaps.core.model.entity.enums.CourseAffiliationType;
import io.roadmaps.core.rest.courses.dto.CreateCourseDto;
import io.roadmaps.core.rest.courses.dto.UpdateCourseDto;
import io.roadmaps.core.rest.courses.dto.response.*;
import io.roadmaps.core.security.annotations.UserId;
import io.roadmaps.core.swagger.ApiPageable;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseRestFacade restFacade;

    @GetMapping("/api/courses")
    @ApiPageable
    public Page<GetAllCoursesResponseDto> getAllCourses(@PageableDefault @Parameter(hidden = true) Pageable pageable,
                                                        @RequestParam(required = false) CourseAffiliationType affiliationType,
                                                        @UserId UUID userId) {
        return restFacade.getAllCourses(pageable, affiliationType, userId);
    }

    @PostMapping("/api/courses")
    public CreateCourseResponseDto createCourse(@RequestBody CreateCourseDto dto, @UserId UUID userId) {
        return restFacade.createCourse(dto, userId);
    }

    @GetMapping("/api/courses/{id}")
    public GetCourseResponseDto getCourse(@PathVariable UUID id, @UserId UUID userId) {
        return restFacade.getCourse(id, userId);
    }

    @PatchMapping("/api/courses/{id}")
    public UpdateCourseResponseDto updateCourse(@PathVariable UUID id, @RequestBody UpdateCourseDto dto, @UserId UUID userId) {
        return restFacade.updateCourse(id, dto, userId);
    }

    @DeleteMapping("/api/courses/{id}")
    public void deleteCourse(@PathVariable UUID id) {
        restFacade.deleteCourse(id);
    }
}
