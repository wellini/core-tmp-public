package io.roadmaps.core.rest.courseaffiliations;

import io.roadmaps.core.exception.EntityNotFoundException;
import io.roadmaps.core.model.entity.Course;
import io.roadmaps.core.model.entity.CourseAffiliation;
import io.roadmaps.core.model.entity.enums.CourseAffiliationType;
import io.roadmaps.core.repository.CourseRepository;
import io.roadmaps.core.rest.courseaffiliations.converters.CourseAffiliationDtoConverter;
import io.roadmaps.core.rest.courseaffiliations.dto.CreateCourseAffiliationForStudentResponseDto;
import io.roadmaps.core.security.AuthorizationService;
import io.roadmaps.core.security.annotations.UserId;
import io.roadmaps.core.service.CourseAffiliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CourseAffiliationController {

    private final CourseAffiliationService affiliationService;
    private final CourseRepository courseRepository;
    private final CourseAffiliationDtoConverter converter;

    @PostMapping("/api/courses/{courseId}/course-affiliations/create-course-affiliation-for-student")
    public CreateCourseAffiliationForStudentResponseDto createCourseAffiliationForStudent(@PathVariable UUID courseId, @UserId UUID userId) {
        Course course = courseRepository.findById(courseId).orElseThrow(EntityNotFoundException::new);
        CourseAffiliation affiliation = affiliationService.createOrUpdateAffiliation(
                course.getId(),
                userId,
                CourseAffiliationType.STUDENT);
        return converter.fromDomain(affiliation, CreateCourseAffiliationForStudentResponseDto.class);
    }
}
