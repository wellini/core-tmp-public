package io.roadmaps.core.domain.model.course.events;

public interface MoveLeafEvent {

    Long getDestinationModuleId();

    int getDestinationOrderId();
}
