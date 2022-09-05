package cc.roadmaps.core.domain.exceptions;

public class NotEnoughPermissionsDomainException extends DomainException {

    public NotEnoughPermissionsDomainException() {
        super("Not enough permissions");
    }
}
