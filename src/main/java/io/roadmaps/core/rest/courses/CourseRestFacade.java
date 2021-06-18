package io.roadmaps.core.rest.courses;

import io.roadmaps.core.exception.EntityNotFoundException;
import io.roadmaps.core.model.entity.Course;
import io.roadmaps.core.model.entity.CourseAffiliation;
import io.roadmaps.core.model.entity.enums.CourseAffiliationType;
import io.roadmaps.core.repository.CourseRepository;
import io.roadmaps.core.rest.courses.converters.CourseDtoConverter;
import io.roadmaps.core.rest.courses.dto.CreateCourseDto;
import io.roadmaps.core.rest.courses.dto.UpdateCourseDto;
import io.roadmaps.core.rest.courses.dto.response.CreateCourseResponseDto;
import io.roadmaps.core.rest.courses.dto.response.GetAllCoursesResponseDto;
import io.roadmaps.core.rest.courses.dto.response.GetCourseResponseDto;
import io.roadmaps.core.rest.courses.dto.response.UpdateCourseResponseDto;
import io.roadmaps.core.security.AuthorizationService;
import io.roadmaps.core.service.CourseAffiliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseRestFacade {

    private final CourseRepository repository;
    private final CourseDtoConverter converter;
    private final CourseAffiliationService affiliationService;
    private final AuthorizationService authorizationService;

    public Page<GetAllCoursesResponseDto> getAllCourses(Pageable pageable, UUID userId) {
        Page<Course> page = repository.findCoursesByAuthorId(userId, pageable);
        List<GetAllCoursesResponseDto> content = page
                .getContent()
                .stream()
                .map(entity -> {
                    GetAllCoursesResponseDto dto = converter.fromDomain(entity, GetAllCoursesResponseDto.class);
                    CourseAffiliationType affiliationType = affiliationService.getAffiliationType(entity.getId(), userId);
                    dto.setAffiliationType(affiliationType);
                    return dto;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @Transactional
    public CreateCourseResponseDto createCourse(CreateCourseDto dto, UUID userId) {
        Course course = converter.toDomain(dto);
        course.setAuthorId(userId);
        Course savedCourse = repository.save(course);
        CourseAffiliation affiliation = affiliationService.createOrUpdateAffiliation(
                savedCourse.getId(),
                userId,
                CourseAffiliationType.TEACHER
        );
        CreateCourseResponseDto responseDto = converter.fromDomain(savedCourse, CreateCourseResponseDto.class);
        responseDto.setAffiliationType(affiliation.getType());
        return responseDto;
    }

    public GetCourseResponseDto getCourse(UUID id,UUID userId) {
        Course course = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        CourseAffiliationType affiliationType = affiliationService.getAffiliationType(
                course.getId(),
                userId
        );
        GetCourseResponseDto responseDto = converter.fromDomain(course, GetCourseResponseDto.class);
        responseDto.setAffiliationType(affiliationType);
        return responseDto;
    }

    public UpdateCourseResponseDto updateCourse(UUID id, UpdateCourseDto dto, UUID userId) {
        Course course = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        converter.update(course, dto);
        Course savedCourse = repository.save(course);
        CourseAffiliationType affiliationType = affiliationService.getAffiliationType(
                course.getId(),
                userId
        );
        UpdateCourseResponseDto responseDto = converter.fromDomain(savedCourse, UpdateCourseResponseDto.class);
        responseDto.setAffiliationType(affiliationType);
        return responseDto;
    }

    @Transactional
    public void deleteCourse(UUID id) {
        affiliationService.deleteAllAffiliationsByCourseId(id);
        repository.deleteById(id);
    }
}
