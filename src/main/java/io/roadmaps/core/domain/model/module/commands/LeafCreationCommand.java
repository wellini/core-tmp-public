package io.roadmaps.core.domain.model.module.commands;

import io.roadmaps.core.domain.model.leaf.enums.LeafType;

import java.util.UUID;

public interface LeafCreationCommand {

    String getTitle();

    LeafType getType();

    int getOrderId();

    String getText();

    String getLink();
}
