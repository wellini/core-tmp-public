package cc.roadmaps.core.service.integrations.web.rest.api.auth.dtos;

import cc.roadmaps.validation.api.Rules;
import cc.roadmaps.validation.api.Validatable;
import cc.roadmaps.validation.api.ValidationFlow;
import cc.roadmaps.validation.exceptions.ValidationException;
import lombok.Getter;

@Getter
public class AuthenticateWithGoogleDto implements Validatable {

    private static final int CODE_MAX_LENGTH = 4096;

    private String code;

    @Override
    public void validate() throws ValidationException {
        // @formatter:off
        ValidationFlow.start()
                .forProperty("code", this::getCode)
                    .strictlyRequire(Rules.notBlank(), "Authorization code should exist")
                    .require(Rules.maxLength(CODE_MAX_LENGTH, Rules.LengthComparisonMode.STANDARD), "Code length should be less than %d".formatted(CODE_MAX_LENGTH))
                    .and()
                .ifHasErrorsThrow(ValidationException::new);
        // @formatter:on
    }
}
