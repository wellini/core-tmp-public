package io.roadmaps.core.integrations.authproviders.services;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.integrations.authproviders.model.AuthProvider;
import io.roadmaps.core.integrations.authproviders.model.AuthProviderRepository;
import io.roadmaps.core.integrations.authproviders.model.enums.AuthProviderType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class AuthProviderServiceImpl implements AuthProviderService {

    private final AuthProviderRepository repository;
    private final Generator<Long> authProviderIdIdSequenceGenerator;

    @Override
    public Long saveAuthentication(Long userId, AuthProviderType type, String providerId, String token) {
        AuthProvider authProvider;
        Optional<AuthProvider> authProviderFromDb = repository.findByUserIdAndType(userId, type);
        if(authProviderFromDb.isPresent()) {
            authProvider = authProviderFromDb.get();
            authProvider.update(providerId, token);
        } else {
            authProvider = AuthProvider.create(authProviderIdIdSequenceGenerator, userId, type, providerId, token);
        }
        repository.save(authProvider);
        return authProvider.getId();
    }
}
