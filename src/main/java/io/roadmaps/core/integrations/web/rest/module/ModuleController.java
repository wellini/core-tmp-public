package io.roadmaps.core.integrations.web.rest.module;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.services.module.ModuleService;
import io.roadmaps.core.integrations.web.rest.common.dtos.IdentifiedCommandExecResponse;
import io.roadmaps.core.integrations.web.rest.leaf.dtos.GetLeafResponse;
import io.roadmaps.core.integrations.web.rest.module.dtos.GetModuleResponse;
import io.roadmaps.core.integrations.web.rest.module.dtos.commands.CreateLeafCommandRequest;
import io.roadmaps.core.integrations.web.rest.module.dtos.commands.EditModuleTitleCommandRequest;
import io.roadmaps.core.integrations.web.rest.module.dtos.commands.RemoveLeafCommandRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/api/courses/modules/{id}/createLeaf")
    public IdentifiedCommandExecResponse createLeaf(@PathVariable UUID id, @RequestBody CreateLeafCommandRequest command) {
        UUID leafId = moduleService.createLeaf(id, command);
        return IdentifiedCommandExecResponse.create(leafId, "Created Leaf with id {${id}}");
    }

    @PostMapping("/api/courses/modules/{id}/editModuleTitle")
    public IdentifiedCommandExecResponse editModuleTitle(@PathVariable UUID id, @RequestBody EditModuleTitleCommandRequest command) {
        UUID moduleId = moduleService.editTitle(id, command);
        return IdentifiedCommandExecResponse.create(moduleId, "Changed title in Module with id {${id}}");
    }

    @PostMapping("/api/courses/modules/{id}/removeLeaf")
    public IdentifiedCommandExecResponse removeLeaf(@PathVariable UUID id, @RequestBody RemoveLeafCommandRequest command) {
        UUID moduleId = moduleService.removeLeaf(id, command.getLeafId());
        return IdentifiedCommandExecResponse.create(moduleId, "Removed leaf from Module with id {${id}}");
    }
}
