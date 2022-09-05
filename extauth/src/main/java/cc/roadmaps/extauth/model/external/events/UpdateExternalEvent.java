package cc.roadmaps.extauth.model.external.events;

public interface UpdateExternalEvent {

    String getExternalId();

    String getExternalAccessToken();

    String getExternalRefreshToken();
}
