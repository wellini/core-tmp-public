package io.roadmaps.core.integrations.web.rest.api.leaf;

import io.roadmaps.core.domain.services.leaf.LeafService;
import io.roadmaps.core.integrations.web.rest.api.leaf.dtos.GetLeafResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LeafController {

    private final LeafService leafService;

    @GetMapping("/api/courses/modules/leaves/{id}")
    public GetLeafResponse getLeaf(@PathVariable Long id) {
        return GetLeafResponse.create(leafService.getLeaf(id));
    }
}
