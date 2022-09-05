package cc.roadmaps.core.service.integrations.web.rest.api.common.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "create")
public class ExplainedErrorResponse extends AbstractErrorResponse {

    private final String message;
}
