package cc.roadmaps.core.service.integrations.web.rest.api.module.dtos;

import cc.roadmaps.core.domain.model.module.Module;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GetModuleResponse {

    @EqualsAndHashCode.Include
    @Schema(required = true)
    private UUID id;

    @Schema(required = true)
    private Integer orderId;

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private UUID courseId;

    private GetModuleResponse(Module module, Integer orderId) {
        id = module.getId();
        this.orderId = orderId;
        title = module.getTitle();
        courseId = module.getCourseId();
    }

    public static GetModuleResponse create(Module module, Integer orderId) {
        return new GetModuleResponse(module, orderId);
    }

    // TODO: Remove
    public static GetModuleResponse create(Module module) {
        return new GetModuleResponse(module, null);
    }
}
