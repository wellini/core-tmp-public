package io.roadmaps.core.service;

import io.roadmaps.core.model.security.AuthProvider;
import io.roadmaps.core.model.security.enums.AuthProviderType;
import io.roadmaps.core.repository.AuthProviderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthProviderService {

    @NonNull
    private final AuthProviderRepository authProviderRepository;

    public void createOrUpdate(UUID userId, AuthProviderType type, String id, String token) {
        AuthProvider authProvider = authProviderRepository.findByUserIdAndType(userId, type).orElse(new AuthProvider());
        authProvider.setToken(token);
        authProvider.setType(type);
        authProvider.setProviderId(id);
        authProvider.setUserId(userId);

        authProviderRepository.save(authProvider);
    }

}
