package io.roadmaps.core.integrations.web.rest.api.course.dtos.commands;

import io.roadmaps.core.domain.services.course.operations.implementations.leafEditTitle.LeafEditTitleCommand;
import io.roadmaps.core.integrations.web.rest.api.course.checks.LeafTitleCheck;
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
public class LeafEditTitleCommandDto extends AbstractCommandDto implements LeafEditTitleCommand {

    @Schema(required = true, type = "string")
    private UUID leafId;

    @Schema(required = true)
    private String title;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("leafId", this::getLeafId)
                    .strictlyRequire(Rules.notNull(), "Set leaf ID")
                    .and()
                .forProperty("title", this::getTitle)
                    .addCheck(LeafTitleCheck.getInstance())
                    .and();
        // @formatter:on
    }
}
