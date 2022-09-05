package cc.roadmaps.extauth.model.external;

import cc.roadmaps.extauth.model.external.events.UpdateExternalEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class External {

    private String externalId;

    private String externalAccessToken;

    private String externalRefreshToken;

    public void update(UpdateExternalEvent event) {
        externalId = event.getExternalId();
        externalAccessToken = event.getExternalAccessToken();
        externalRefreshToken = event.getExternalRefreshToken();
    }
}
