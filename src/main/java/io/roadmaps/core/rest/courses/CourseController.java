package io.roadmaps.core.rest.courses;

import io.roadmaps.core.exception.EntityNotFoundException;
import io.roadmaps.core.model.entity.Course;
import io.roadmaps.core.repository.CourseRepository;
import io.roadmaps.core.rest.courses.converters.CourseDtoConverter;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.roadmaps.core.model.entity.User.DEFAULT_USER_ID;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository repository;
    private final CourseDtoConverter converter;

    @GetMapping("/api/courses")
    @ApiPageable
    public Page<GetAllCoursesResponseDto> getAllCourses(@PageableDefault @Parameter(hidden = true) Pageable pageable) {
        Page<Course> page = repository.findCoursesByAuthorId(DEFAULT_USER_ID, pageable);
        List<GetAllCoursesResponseDto> content = page
                .getContent()
                .stream()
                .map(entity -> converter.fromDomain(entity, GetAllCoursesResponseDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @PostMapping("/api/courses")
    public CreateCourseResponseDto createCourse(@RequestBody CreateCourseDto dto) {
        Course course = converter.toDomain(dto);
        course.setAuthorId(DEFAULT_USER_ID);
        return converter.fromDomain(repository.save(course), CreateCourseResponseDto.class);
    }

    @GetMapping("/api/courses/{id}")
    public GetCourseResponseDto getCourse(@PathVariable UUID id) {
        Course course = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return converter.fromDomain(course, GetCourseResponseDto.class);
    }

    @PatchMapping("/api/courses/{id}")
    public UpdateCourseResponseDto updateCourse(@PathVariable UUID id, @RequestBody UpdateCourseDto dto) {
        Course course = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        converter.update(course, dto);
        return converter.fromDomain(repository.save(course), UpdateCourseResponseDto.class);
    }

    @DeleteMapping("/api/courses/{id}")
    public void deleteCourse(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
