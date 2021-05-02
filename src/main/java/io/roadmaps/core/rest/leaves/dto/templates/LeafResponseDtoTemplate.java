package io.roadmaps.core.rest.leaves.dto.templates;

import io.roadmaps.core.model.entity.enums.LeafType;
import lombok.Data;

import java.util.UUID;

@Data
public class LeafResponseDtoTemplate {

    private UUID id;

    private String title;

    private LeafType type;

    private Integer orderId;

    private String link;

    private String text;

    private UUID moduleId;

    private UUID courseId;
}