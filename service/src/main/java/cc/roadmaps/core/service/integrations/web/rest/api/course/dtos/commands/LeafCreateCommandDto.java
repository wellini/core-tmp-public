package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands;

import cc.roadmaps.core.domain.model.leaf.enums.LeafType;
import cc.roadmaps.core.domain.services.course.operations.implementations.leafCreate.LeafCreateCommand;
import cc.roadmaps.core.service.integrations.web.rest.api.course.checks.LeafTitleCheck;
import cc.roadmaps.validation.api.Rules;
import cc.roadmaps.validation.api.ValidationFlow;
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

    @Schema(required = true)
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
                    .addCheck(LeafTitleCheck.getInstance())
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
