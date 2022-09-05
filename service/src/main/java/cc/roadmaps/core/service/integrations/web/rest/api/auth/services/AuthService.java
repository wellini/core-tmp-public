package cc.roadmaps.core.service.integrations.web.rest.api.auth.services;

import cc.roadmaps.extauth.model.AuthProvider;
import cc.roadmaps.extauth.services.AuthenticationManager;
import cc.roadmaps.extauth.services.specific.google.GoogleAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public String authenticateWithGoogle(String code) {
        GoogleAuthentication googleAuthentication = GoogleAuthentication.create(code);
        AuthProvider authProvider = authenticationManager.authenticate(googleAuthentication);
        return jwtTokenService.generate(authProvider.getUserId());
    }
}
