package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.responses.commandExecution;

import cc.roadmaps.core.domain.services.course.operations.ExplainedExecResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Objects;

@Schema(
        oneOf = {
                IdentifiedCommandExecResponse.class,
                VoidCommandExecResponse.class
        }
)
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
