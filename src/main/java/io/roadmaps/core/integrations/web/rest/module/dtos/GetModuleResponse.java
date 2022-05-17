package io.roadmaps.core.integrations.web.rest.module.dtos;

import io.roadmaps.core.domain.model.module.Module;
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

    @Schema(required = false)
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

    public static GetModuleResponse create(Module module) {
        return new GetModuleResponse(module, null);
    }
}
