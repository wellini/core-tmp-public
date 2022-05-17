package io.roadmaps.core.integrations.web.rest.common.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.roadmaps.core.utils.StringSubstitutor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public abstract class AbstractCommandExecResponse {
    /**
     * Description of command execution result
     */
    @Schema(required = true)
    private String message;

    @JsonIgnore
    protected final StringSubstitutor substitutor = StringSubstitutor.create();

    public AbstractCommandExecResponse(String message) {
        this.message = message;
    }

    @JsonIgnore
    public String getOriginalMessage() {
        return message;
    }

    public String getMessage() {
        return substitutor.replace(message);
    }
}
