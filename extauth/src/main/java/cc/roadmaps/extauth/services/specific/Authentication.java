package cc.roadmaps.extauth.services.specific;

import cc.roadmaps.extauth.model.enums.AuthProviderType;

public interface Authentication<T> {

    AuthProviderType getProviderType();

    T getData();
}
