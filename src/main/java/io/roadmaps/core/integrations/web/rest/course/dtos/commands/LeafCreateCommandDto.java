package io.roadmaps.core.integrations.web.rest.course.dtos.commands;

import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import io.roadmaps.core.domain.services.course.operations.implementations.leafCreate.LeafCreateCommand;
import io.roadmaps.core.validation.Rules;
import io.roadmaps.core.validation.ValidationFlow;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LeafCreateCommandDto extends AbstractCommandDto implements LeafCreateCommand {

    @Schema(required = true, type = "string")
    private UUID moduleId;

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private LeafType type;

    @Schema(required = true)
    private Integer orderId;

    @Schema(required = false)
    private String text;

    @Schema(required = false)
    private String link;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("moduleId", this::getModuleId)
                    .strictlyRequire(Rules.notNull(), "Set module ID")
                    .and()
                .forProperty("title", this::getTitle)
                    .strictlyRequire(Rules.notBlank(), "Set title")
                    .require(Rules.maxLength(256, Rules.LengthComparisonMode.TRIMMED), "Length should be less than 256")
                    .and()
                .forProperty("type", this::getType)
                    .strictlyRequire(Rules.notNull(), "Set type")
                    .and()
                .forProperty("orderId", this::getOrderId)
                    .strictlyRequire(Rules.notNull(), "Set order")
                    .and()
                .forProperty("link", this::getLink)
                    .strictlyRequire(this::linkIsPresentForLinkLeaf, "Link property should exist for link leaves")
                    .require(Rules.maxLength(512), "Length should be less than 512")
                    .and();
        // @formatter:on
    }

    private boolean linkIsPresentForLinkLeaf(String link) {
        return (this.getType() != LeafType.LINK && Objects.isNull(link)) || (this.getType() == LeafType.LINK && Objects.nonNull(link) && !link.isBlank());
    }
}
