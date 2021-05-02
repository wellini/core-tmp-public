package io.roadmaps.core.rest.modules;

import io.roadmaps.core.exception.EntityNotFoundException;
import io.roadmaps.core.model.entity.Course;
import io.roadmaps.core.model.entity.Module;
import io.roadmaps.core.repository.CourseRepository;
import io.roadmaps.core.repository.ModuleRepository;
import io.roadmaps.core.rest.modules.converters.ModuleDtoConverter;
import io.roadmaps.core.rest.modules.dto.response.CreateModuleResponseDto;
import io.roadmaps.core.rest.modules.dto.response.GetModuleResponseDto;
import io.roadmaps.core.rest.modules.dto.response.GetModulesByCourseIdResponseDto;
import io.roadmaps.core.rest.modules.dto.CreateModuleDto;
import io.roadmaps.core.rest.modules.dto.UpdateModuleDto;
import io.roadmaps.core.rest.modules.dto.response.UpdateModuleResponseDto;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
public class ModulesController {

    private final ModuleRepository repository;
    private final CourseRepository courseRepository;
    private final ModuleDtoConverter converter;

    @GetMapping("/api/courses/{courseId}/modules")
    public List<GetModulesByCourseIdResponseDto> getModulesByCourseId(@PathVariable UUID courseId) {
        return repository.findAllByCourseId(courseId).stream()
                .map(entity -> converter.fromDomain(entity, GetModulesByCourseIdResponseDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/courses/modules/{id}")
    public GetModuleResponseDto getModule(@PathVariable UUID id) {
        return converter.fromDomain(repository.findById(id).orElseThrow(EntityNotFoundException::new), GetModuleResponseDto.class);
    }

    @PostMapping("/api/courses/{courseId}/modules")
    public CreateModuleResponseDto createModule(@PathVariable UUID courseId, @RequestBody CreateModuleDto dto) {
        Course course = courseRepository.findById(courseId).orElseThrow(EntityNotFoundException::new);
        Module module = converter.toDomain(dto);
        module.setCourseId(course.getId());
        return converter.fromDomain(repository.save(module), CreateModuleResponseDto.class);
    }

    @PatchMapping("/api/courses/modules/{id}")
    public UpdateModuleResponseDto updateModule(@PathVariable UUID id, @RequestBody UpdateModuleDto dto) {
        Module module = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        converter.update(module, dto);
        return converter.fromDomain(repository.save(module), UpdateModuleResponseDto.class);
    }

    @DeleteMapping("/api/courses/modules/{id}")
    public void deleteModule(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
