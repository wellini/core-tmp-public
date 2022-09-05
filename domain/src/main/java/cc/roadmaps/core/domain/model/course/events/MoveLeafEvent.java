package cc.roadmaps.core.domain.model.course.events;

import java.util.UUID;

public interface MoveLeafEvent {

    UUID getDestinationModuleId();

    int getDestinationOrderId();
}
