package io.roadmaps.core.rest.auth;

import io.roadmaps.core.rest.auth.dto.LoginByGoogleProviderDto;
import io.roadmaps.core.rest.auth.dto.response.LoginByGoogleProviderResponseDto;
import io.roadmaps.core.security.AuthorizationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @NonNull
    private final AuthorizationService authorizationService;

    @PostMapping("/login/provider/google")
    public LoginByGoogleProviderResponseDto loginByGoogleProvider(@RequestBody LoginByGoogleProviderDto body) {
        return authorizationService.loginByGoogle(body.getCode());
    }

}
