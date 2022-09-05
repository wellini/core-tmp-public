package cc.roadmaps.extauth.services.authprovider.events;

import cc.roadmaps.extauth.model.enums.AuthProviderType;
import cc.roadmaps.extauth.model.external.events.UpdateExternalEvent;

import java.util.UUID;

public interface AuthProviderSavingEvent extends UpdateExternalEvent {

    UUID getUserId();

    AuthProviderType getType();
}
