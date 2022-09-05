package cc.roadmaps.core.domain.services.course.operations.implementations.leafUpdateText;

import cc.roadmaps.core.domain.model.leaf.events.UpdateTextEvent;
import cc.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface LeafUpdateTextCommand extends Command, UpdateTextEvent {

    @Override
    UUID getLeafId();
}
