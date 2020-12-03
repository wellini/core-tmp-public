package edu.roadmaps.core.service.attachment;

import edu.roadmaps.core.model.entity.attachment.Attachment;
import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.rest.leaves.attachment.dto.AttachmentInCreateDto;
import edu.roadmaps.core.rest.leaves.attachment.dto.AttachmentInDetailDto;
import edu.roadmaps.core.service.leaf.LeafServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AttachmentServiceImpl implements AttachmentService {

    private final ModelMapper modelMapper;
    private final LeafServiceImpl leafService;

    @Autowired
    public AttachmentServiceImpl(ModelMapper modelMapper, LeafServiceImpl leafService) {
        this.modelMapper = modelMapper;
        this.leafService = leafService;
    }

    @Override
    public AttachmentInDetailDto addAttachment(AttachmentInCreateDto dto, UUID lectureId) {
        Leaf leaf = leafService.get(lectureId);
        Attachment attachment = modelMapper.map(dto, Attachment.class);
        attachment.setId(UUID.randomUUID());
        List<Attachment> attachments = leaf.getAttachments();
        attachments.add(attachment);
        leaf.setAttachments(attachments);
        leafService.create(leaf);
        return modelMapper.map(attachment, AttachmentInDetailDto.class);
    }

    @Override
    public void deleteAttachment(UUID leafId, UUID attachId) {
        Leaf leaf = leafService.get(leafId);
        List<Attachment> attachments = leaf.getAttachments()
                .stream()
                .filter(att -> att.getId().compareTo(attachId) != 0)
                .collect(Collectors.toList());
        leaf.setAttachments(attachments);
    }

}
