package cc.roadmaps.core.service.integrations.web.rest.api.auth.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "create")
public class InternalTokenResponse {

    @Schema(required = true)
    private final String token;
}
