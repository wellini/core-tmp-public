package edu.roadmaps.core.rest.leaves.attachment.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AttachmentInDetailDto {
    UUID id;
    String url;
    Integer orderId;
}
