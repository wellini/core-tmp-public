package io.roadmaps.core.integrations.web.rest.leaf;

import io.roadmaps.core.domain.services.leaf.LeafService;
import io.roadmaps.core.integrations.web.rest.common.dtos.IdentifiedCommandExecResponse;
import io.roadmaps.core.integrations.web.rest.leaf.dtos.GetLeafResponse;
import io.roadmaps.core.integrations.web.rest.leaf.dtos.commands.EditLeafTitleCommandRequest;
import io.roadmaps.core.integrations.web.rest.leaf.dtos.commands.UpdateTextCommandRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/api/courses/modules/leaves/{id}/editLeafTitle")
    public IdentifiedCommandExecResponse editLeafTitle(@PathVariable UUID id, @RequestBody EditLeafTitleCommandRequest command) {
        UUID leafId = leafService.editTitle(id, command);
        return IdentifiedCommandExecResponse.create(leafId, "Changed title for leaf with id {${id}}");
    }

    @PostMapping("/api/courses/modules/leaves/{id}/updateText")
    public IdentifiedCommandExecResponse updateText(@PathVariable UUID id, @RequestBody UpdateTextCommandRequest command) {
        UUID leafId = leafService.updateText(id, command);
        return IdentifiedCommandExecResponse.create(leafId, "Updated text for leaf with id {${id}}");
    }
}
