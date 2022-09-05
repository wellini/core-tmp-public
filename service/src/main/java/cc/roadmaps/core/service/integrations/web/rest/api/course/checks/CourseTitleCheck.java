package cc.roadmaps.core.service.integrations.web.rest.api.course.checks;

import cc.roadmaps.validation.api.Check;
import cc.roadmaps.validation.api.Rules;
import cc.roadmaps.validation.api.ValidationFlow;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseTitleCheck implements Check<String> {

    private static final int MAX_LENGTH = 256;

    @Getter
    private static final CourseTitleCheck instance = new CourseTitleCheck();

    @Override
    public void configureValidationFlow(ValidationFlow<String> validationFlow, String propertyName, Supplier<String> propertySupplier) {
        // formatter:off
        validationFlow
                .strictlyRequire(Rules.notBlank(), "Set title")
                .require(Rules.maxLength(MAX_LENGTH, Rules.LengthComparisonMode.TRIMMED), String.format("Length should be less than %d", MAX_LENGTH));
        // formatter:on
    }
}
