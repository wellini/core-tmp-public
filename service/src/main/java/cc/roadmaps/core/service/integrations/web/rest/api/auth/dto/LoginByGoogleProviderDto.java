package cc.roadmaps.core.service.integrations.web.rest.api.auth.dto;

import lombok.Data;

@Data
public class LoginByGoogleProviderDto {

    private String code;

}
