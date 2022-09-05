package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.responses.commandExecution;

public class VoidCommandExecResponse extends AbstractCommandExecResponse {

    private VoidCommandExecResponse(String message) {
        super(message);
    }

    public static VoidCommandExecResponse create(String message) {
        return new VoidCommandExecResponse(message);
    }
}
