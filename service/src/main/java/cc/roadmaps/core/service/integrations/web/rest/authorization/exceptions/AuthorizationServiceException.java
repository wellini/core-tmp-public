package cc.roadmaps.core.service.integrations.web.rest.authorization.exceptions;

import cc.roadmaps.core.service.exceptions.RoadmapsServiceException;

public class AuthorizationServiceException extends RoadmapsServiceException {

    public AuthorizationServiceException() {
        super("Not authorized");
    }
}
