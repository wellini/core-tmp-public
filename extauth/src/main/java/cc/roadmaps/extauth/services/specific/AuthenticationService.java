package cc.roadmaps.extauth.services.specific;

import cc.roadmaps.extauth.model.AuthProvider;

public interface AuthenticationService<T extends Authentication<?>> {

    boolean canAuthenticate(Authentication<?> auth);

    AuthProvider authenticate(T auth);
}
