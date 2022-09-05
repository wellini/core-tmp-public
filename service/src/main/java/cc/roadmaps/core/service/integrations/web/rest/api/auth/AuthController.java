package cc.roadmaps.core.service.integrations.web.rest.api.auth;

import cc.roadmaps.core.service.integrations.web.rest.api.auth.dtos.AuthenticateWithGoogleDto;
import cc.roadmaps.core.service.integrations.web.rest.api.auth.dtos.InternalTokenResponse;
import cc.roadmaps.core.service.integrations.web.rest.api.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/google")
    public InternalTokenResponse authenticateWithGoogle(@RequestBody AuthenticateWithGoogleDto body) {
        body.validate();
        return InternalTokenResponse.create(authService.authenticateWithGoogle(body.getCode()));
    }
}
