package cc.roadmaps.core.service.integrations.web.rest.api.auth.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginByGoogleProviderResponseDto {

    private String accessToken;

}
