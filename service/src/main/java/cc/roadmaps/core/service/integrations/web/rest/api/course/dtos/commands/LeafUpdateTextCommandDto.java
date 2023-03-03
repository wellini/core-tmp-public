package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands;

import cc.roadmaps.core.domain.services.course.operations.implementations.leafUpdateText.LeafUpdateTextCommand;
import cc.roadmaps.validation.api.Rules;
import cc.roadmaps.validation.api.ValidationFlow;
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
public class LeafUpdateTextCommandDto extends AbstractCommandDto implements LeafUpdateTextCommand {

    @Schema(required = true)
    private UUID leafId;

    @Schema(required = true)
    private String text;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("leafId", this::getLeafId)
                    .strictlyRequire(Rules.notNull(), "Set leaf ID")
                    .and()
                .forProperty("text", this::getText)
                    .strictlyRequire(Rules.notBlank(), "Set text")
                    .and();
        // @formatter:on
    }
}
