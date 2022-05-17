package io.roadmaps.core.integrations.web;

import io.roadmaps.core.domain.services.CurrentUserIdProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CurrentUserIdProviderImpl implements CurrentUserIdProvider {

    @Override
    public Optional<UUID> getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        } else {
            return Optional.of(UUID.fromString((String) authentication.getPrincipal()));
        }
    }
}
