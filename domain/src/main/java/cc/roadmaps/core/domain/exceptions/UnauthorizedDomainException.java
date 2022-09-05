package cc.roadmaps.core.domain.exceptions;

public class UnauthorizedDomainException extends DomainException {

    public UnauthorizedDomainException() {
        super("Unauthorized");
    }
}
