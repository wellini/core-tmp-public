package io.roadmaps.core.integrations.web.rest.module;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.services.module.ModuleService;
import io.roadmaps.core.integrations.web.rest.leaf.dtos.GetLeafResponse;
import io.roadmaps.core.integrations.web.rest.module.dtos.GetModuleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping("/api/courses/modules/{id}")
    public GetModuleResponse getModule(@PathVariable UUID id) {
        return GetModuleResponse.create(moduleService.getModule(id));
    }

    @GetMapping("/api/courses/modules/{id}/leaves")
    public List<GetLeafResponse> getLeavesInModule(@PathVariable UUID id) {
        List<Leaf> leaves = moduleService.getLeavesInModule(id);
        ArrayList<GetLeafResponse> response = new ArrayList<>(leaves.size());
        for (int i = 0; i < leaves.size(); i++) {
            response.add(GetLeafResponse.create(leaves.get(i), i));
        }
        return response;
    }
}
