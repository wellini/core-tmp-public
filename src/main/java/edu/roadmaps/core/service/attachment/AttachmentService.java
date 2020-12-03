package edu.roadmaps.core.service.attachment;

import edu.roadmaps.core.rest.leaves.attachment.dto.AttachmentInCreateDto;
import edu.roadmaps.core.rest.leaves.attachment.dto.AttachmentInDetailDto;

import java.util.UUID;

public interface AttachmentService {
    AttachmentInDetailDto addAttachment(AttachmentInCreateDto dto, UUID lectureId);
    void deleteAttachment(UUID lectureId, UUID attachId);
}
