package io.roadmaps.core.domain.services.leaf;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.leaf.commands.EditLeafTitleCommand;
import io.roadmaps.core.domain.model.leaf.commands.UpdateTextCommand;

import java.util.List;
import java.util.UUID;

public interface LeafService {

    Leaf getLeaf(UUID id);

    UUID editTitle(UUID id, EditLeafTitleCommand editLeafTitleCommand);

    UUID updateText(UUID id, UpdateTextCommand updateTextCommand);
}
