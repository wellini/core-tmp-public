package edu.roadmaps.core.rest.leaves.lecture.dto;

import edu.roadmaps.core.model.entity.attachment.Attachment;
import edu.roadmaps.core.rest.dto.leaf.LeafDetailDto;
import edu.roadmaps.core.rest.dto.leaf.LeafDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class LectureFullDetailLeafDto extends LeafDetailDto {
    private List<Attachment> attachments;
}
