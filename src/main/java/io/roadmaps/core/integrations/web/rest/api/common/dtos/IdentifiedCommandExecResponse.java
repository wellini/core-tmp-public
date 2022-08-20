package io.roadmaps.core.integrations.web.rest.api.common.dtos;

import io.roadmaps.core.hrid.HRIDUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class IdentifiedCommandExecResponse extends AbstractCommandExecResponse {

    /**
     * ID of created/changed entity
     */
    @Schema(required = true)
    private String id;

    private IdentifiedCommandExecResponse(String id, String message) {
        super(message);
        this.id = id;
    }

    public static IdentifiedCommandExecResponse create(Long id, String message) {
        return new IdentifiedCommandExecResponse(HRIDUtil.serialize(id), message);
    }
}
