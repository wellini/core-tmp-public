package cc.roadmaps.core.service.integrations.web.rest.api.auth.exceptions;

import cc.roadmaps.core.service.exceptions.RoadmapsServiceException;

public class AuthorizationServiceException extends RoadmapsServiceException {

    public AuthorizationServiceException() {
        super("Not authorized");
    }
}
