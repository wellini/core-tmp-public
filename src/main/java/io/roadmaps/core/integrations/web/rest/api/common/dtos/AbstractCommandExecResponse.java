package io.roadmaps.core.integrations.web.rest.api.common.dtos;

import io.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class AbstractCommandExecResponse {

    /**
     * Description of command execution result
     */
    @Schema(required = true)
    @Getter
    private String message;

    protected AbstractCommandExecResponse(String message) {
        this.message = message;
    }

    public static AbstractCommandExecResponse create(ExplainedExecResult result) {
        if (Objects.nonNull(result.getId())) {
            return IdentifiedCommandExecResponse.create(result.getId(), result.getMessage());
        } else {
            return VoidCommandExecResponse.create(result.getMessage());
        }
    }
}
