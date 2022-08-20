package io.roadmaps.core.domain.services.course.operations.implementations.leafEditTitle;

import io.roadmaps.core.domain.model.leaf.events.EditLeafTitleEvent;
import io.roadmaps.core.domain.services.course.operations.commands.Command;

public interface LeafEditTitleCommand extends Command, EditLeafTitleEvent {

    @Override
    Long getLeafId();
}
