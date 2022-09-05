package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.responses.commandExecution;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;

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

    public static IdentifiedCommandExecResponse create(UUID id, String message) {
        return new IdentifiedCommandExecResponse(id.toString(), message);
    }
}
