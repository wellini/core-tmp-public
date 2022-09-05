package cc.roadmaps.extauth.services.authprovider;

import cc.roadmaps.extauth.model.AuthProvider;
import cc.roadmaps.extauth.services.authprovider.events.AuthProviderSavingEvent;

public interface AuthProviderService {

    AuthProvider createOrUpdate(AuthProviderSavingEvent command);
}
