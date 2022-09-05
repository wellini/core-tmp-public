package cc.roadmaps.extauth.services;

import cc.roadmaps.extauth.model.AuthProvider;
import cc.roadmaps.extauth.services.specific.Authentication;

public interface AuthenticationManager {

    AuthProvider authenticate(Authentication authentication);
}
