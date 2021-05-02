package io.roadmaps.core.rest.modules.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class CreateModuleDto {

    @Parameter(required = true)
    private String title;

    private Integer orderId;
}
