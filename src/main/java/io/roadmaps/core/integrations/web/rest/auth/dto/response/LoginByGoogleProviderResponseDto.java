package io.roadmaps.core.integrations.web.rest.auth.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginByGoogleProviderResponseDto {

    private String accessToken;

}
