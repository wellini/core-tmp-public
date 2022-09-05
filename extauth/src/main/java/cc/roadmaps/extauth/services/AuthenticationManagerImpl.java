package cc.roadmaps.extauth.services;

import cc.roadmaps.extauth.exceptions.AuthenticationNotSupportedExternalAuthenticationException;
import cc.roadmaps.extauth.model.AuthProvider;
import cc.roadmaps.extauth.services.specific.Authentication;
import cc.roadmaps.extauth.services.specific.AuthenticationService;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class AuthenticationManagerImpl implements AuthenticationManager {

    private final Collection<AuthenticationService<Authentication<?>>> services;

    @Override
    public AuthProvider authenticate(Authentication authentication) {
        Optional<AuthenticationService<Authentication<?>>> handler = services.stream()
                .filter(service -> service.canAuthenticate(authentication))
                .findFirst();

        if(handler.isPresent()) {
            return handler.get().authenticate(authentication);
        } else {
            throw new AuthenticationNotSupportedExternalAuthenticationException();
        }
    }
}
