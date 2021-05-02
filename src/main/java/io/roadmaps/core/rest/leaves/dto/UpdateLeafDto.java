package io.roadmaps.core.rest.leaves.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateLeafDto {

    private String title;

    private Integer orderId;

    private String link;

    private String text;

    private UUID moduleId;
}
