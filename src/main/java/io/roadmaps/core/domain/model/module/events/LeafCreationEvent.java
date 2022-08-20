package io.roadmaps.core.domain.model.module.events;

import io.roadmaps.core.domain.model.leaf.enums.LeafType;

public interface LeafCreationEvent {

    String getTitle();

    LeafType getType();

    Integer getOrderId();

    String getText();

    String getLink();
}
