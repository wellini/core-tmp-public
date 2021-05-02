package io.roadmaps.core.rest.leaves;

import io.roadmaps.core.exception.EntityNotFoundException;
import io.roadmaps.core.model.entity.Leaf;
import io.roadmaps.core.model.entity.Module;
import io.roadmaps.core.repository.LeafRepository;
import io.roadmaps.core.repository.ModuleRepository;
import io.roadmaps.core.rest.leaves.converter.LeafDtoConverter;
import io.roadmaps.core.rest.leaves.dto.CreateLeafDto;
import io.roadmaps.core.rest.leaves.dto.UpdateLeafDto;
import io.roadmaps.core.rest.leaves.dto.response.CreateLeafResponseDto;
import io.roadmaps.core.rest.leaves.dto.response.GetLeafResponseDto;
import io.roadmaps.core.rest.leaves.dto.response.GetLeavesByCourseIdResponseDto;
import io.roadmaps.core.rest.leaves.dto.response.UpdateLeafResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LeafController {

    private final LeafRepository repository;
    private final ModuleRepository moduleRepository;
    private final LeafDtoConverter converter;

    @PostMapping("/api/courses/modules/{moduleId}/lectures")
    public CreateLeafResponseDto createLeaf(@PathVariable UUID moduleId, @RequestBody CreateLeafDto dto) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(EntityNotFoundException::new);
        Leaf leaf = converter.toDomain(dto);
        leaf.setCourseId(module.getCourseId());
        leaf.setModuleId(module.getId());
        return converter.fromDomain(repository.save(leaf), CreateLeafResponseDto.class);
    }

    @GetMapping("/api/courses/{courseId}/modules/lectures")
    public List<GetLeavesByCourseIdResponseDto> getLeavesByCourseId(@PathVariable UUID courseId) {
        return repository.findAllByCourseId(courseId).stream()
                .map(leaf -> converter.fromDomain(leaf, GetLeavesByCourseIdResponseDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/courses/modules/lectures/{id}")
    public GetLeafResponseDto getLeaf(@PathVariable UUID id) {
        return converter.fromDomain(repository.findById(id).orElseThrow(EntityNotFoundException::new), GetLeafResponseDto.class);
    }

    @PatchMapping("/api/courses/modules/lectures/{id}")
    public UpdateLeafResponseDto updateLeaf(@PathVariable UUID id, @RequestBody UpdateLeafDto dto) {
        Leaf leaf = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        converter.update(leaf, dto);
        return converter.fromDomain(repository.save(leaf), UpdateLeafResponseDto.class);
    }

    @DeleteMapping("/api/courses/modules/lectures/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLeaf(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
