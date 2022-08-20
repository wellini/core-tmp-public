package io.roadmaps.core.integrations.web.rest.api.common.dtos;

public class VoidCommandExecResponse extends AbstractCommandExecResponse {

    private VoidCommandExecResponse(String message) {
        super(message);
    }

    public static VoidCommandExecResponse create(String message) {
        return new VoidCommandExecResponse(message);
    }
}
