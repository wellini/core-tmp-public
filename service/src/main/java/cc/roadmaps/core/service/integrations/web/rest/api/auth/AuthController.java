package cc.roadmaps.core.service.integrations.web.rest.api.auth;

import cc.roadmaps.core.service.integrations.web.rest.api.auth.dto.LoginByGoogleProviderDto;
import cc.roadmaps.core.service.integrations.web.rest.api.auth.dto.response.LoginByGoogleProviderResponseDto;
import cc.roadmaps.core.service.integrations.web.rest.authorization.services.AuthorizationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @NonNull
    private final AuthorizationService authorizationService;

    @PostMapping("/login/provider/google")
    public LoginByGoogleProviderResponseDto loginByGoogleProvider(@RequestBody LoginByGoogleProviderDto body) {
        log.info("google code {}", body.getCode());
        return authorizationService.loginByGoogle(body.getCode());
    }
}
