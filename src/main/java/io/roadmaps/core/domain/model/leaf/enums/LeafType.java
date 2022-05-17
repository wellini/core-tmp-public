package io.roadmaps.core.domain.model.leaf.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum LeafType {
    LINK, TEXT
}
