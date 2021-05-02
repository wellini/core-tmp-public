package io.roadmaps.core.rest.modules.dto.templates;

import lombok.Data;

import java.util.UUID;

@Data
public class ModuleResponseDtoTemplate {

    private UUID id;

    private Integer orderId;

    private String title;

    private UUID courseId;
}
