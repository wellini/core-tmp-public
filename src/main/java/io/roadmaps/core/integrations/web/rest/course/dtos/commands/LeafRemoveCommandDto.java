package io.roadmaps.core.integrations.web.rest.course.dtos.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.roadmaps.core.domain.services.course.operations.implementations.leafRemove.LeafRemoveCommand;
import io.roadmaps.core.validation.Rules;
import io.roadmaps.core.validation.ValidationFlow;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LeafRemoveCommandDto extends AbstractCommandDto implements LeafRemoveCommand {

    @Schema(required = true, type = "string")
    @JsonProperty("leafId")
    private UUID leafId;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("leafId", this::getLeafId)
                .strictlyRequire(Rules.notNull(), "Set leaf ID")
                .and();
        // @formatter:on
    }
}
