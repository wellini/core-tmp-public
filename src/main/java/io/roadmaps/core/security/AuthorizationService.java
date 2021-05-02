package io.roadmaps.core.security;

import io.roadmaps.core.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationService {

    public UUID getCurrentUserId() {
        return User.DEFAULT_USER_ID;
    }
}
