package io.roadmaps.core.domain.services.course.operations.implementations.leafUpdateText;

import io.roadmaps.core.domain.model.leaf.events.UpdateTextEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

public interface LeafUpdateTextCommand extends Command, UpdateTextEvent {

    @Override
    Long getLeafId();
}
