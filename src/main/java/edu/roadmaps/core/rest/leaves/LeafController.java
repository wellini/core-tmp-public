package edu.roadmaps.core.rest.leaves;

import edu.roadmaps.core.rest.leaves.dto.LeafInCreateDto;
import edu.roadmaps.core.rest.leaves.dto.LeafInUpdateDto;
import edu.roadmaps.core.rest.leaves.dto.LectureFullDetailLeafDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses/modules")
@RequiredArgsConstructor
public class LeafController {

    private final LeafControllerRestFacade facade;

    @PostMapping("/{moduleId}/lectures")
    public LectureFullDetailLeafDto create(@PathVariable UUID moduleId, @RequestBody LeafInCreateDto dto) {
        return facade.create(moduleId, dto);
    }

    @GetMapping("/lectures/{id}")
    public LectureFullDetailLeafDto get(@PathVariable UUID id) {
        return facade.get(id);
    }

    @PatchMapping("/lectures/{id}")
    public LectureFullDetailLeafDto update(@PathVariable UUID id, @RequestBody LeafInUpdateDto dto) {
        return facade.update(id, dto);
    }

    @DeleteMapping("/lectures/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        facade.delete(id);
    }
}
