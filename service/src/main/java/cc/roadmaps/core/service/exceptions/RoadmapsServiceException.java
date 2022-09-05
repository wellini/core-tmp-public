package cc.roadmaps.core.service.exceptions;

public abstract class RoadmapsServiceException extends RuntimeException {

    public RoadmapsServiceException(String message) {
        super(message);
    }

    public RoadmapsServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoadmapsServiceException(Throwable cause) {
        super(cause);
    }

    public RoadmapsServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
