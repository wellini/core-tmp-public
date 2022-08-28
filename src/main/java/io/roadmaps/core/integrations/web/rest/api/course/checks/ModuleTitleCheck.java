package io.roadmaps.core.integrations.web.rest.api.course.checks;

import io.roadmaps.core.validation.Check;
import io.roadmaps.core.validation.Rules;
import io.roadmaps.core.validation.ValidationFlow;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModuleTitleCheck implements Check<String> {

    private static final int MAX_LENGTH = 256;

    @Getter
    private static final ModuleTitleCheck instance = new ModuleTitleCheck();

    @Override
    public void configureValidationFlow(ValidationFlow<String> validationFlow, String propertyName, Supplier<String> propertySupplier) {
        // formatter:off
        validationFlow
                .strictlyRequire(Rules.notBlank(), "Set title")
                .require(Rules.maxLength(MAX_LENGTH, Rules.LengthComparisonMode.TRIMMED), String.format("Length should be less than %d", MAX_LENGTH));
        // formatter:on
    }
}
