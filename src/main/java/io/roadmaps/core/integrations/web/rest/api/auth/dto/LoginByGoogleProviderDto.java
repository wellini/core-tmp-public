package io.roadmaps.core.integrations.web.rest.api.auth.dto;

import lombok.Data;

@Data
public class LoginByGoogleProviderDto {

    private String code;

}
