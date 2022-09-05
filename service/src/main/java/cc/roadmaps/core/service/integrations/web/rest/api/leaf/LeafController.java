package cc.roadmaps.core.service.integrations.web.rest.api.leaf;

import cc.roadmaps.core.domain.services.leaf.LeafService;
import cc.roadmaps.core.service.integrations.web.rest.api.leaf.dtos.GetLeafResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LeafController {

    private final LeafService leafService;

    @GetMapping("/api/courses/modules/leaves/{id}")
    public GetLeafResponse getLeaf(@PathVariable UUID id) {
        return GetLeafResponse.create(leafService.getLeaf(id));
    }
}
