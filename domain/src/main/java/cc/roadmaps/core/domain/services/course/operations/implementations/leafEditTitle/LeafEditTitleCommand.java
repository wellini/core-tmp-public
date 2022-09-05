package cc.roadmaps.core.domain.services.course.operations.implementations.leafEditTitle;

import cc.roadmaps.core.domain.model.leaf.events.EditLeafTitleEvent;
import cc.roadmaps.core.domain.services.course.operations.commands.Command;

import java.util.UUID;

public interface LeafEditTitleCommand extends Command, EditLeafTitleEvent {

    @Override
    UUID getLeafId();
}
