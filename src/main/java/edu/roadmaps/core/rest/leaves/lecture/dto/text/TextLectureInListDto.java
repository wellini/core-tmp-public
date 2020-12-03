package edu.roadmaps.core.rest.leaves.lecture.dto.text;

import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.rest.dto.leaf.LeafDetailDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class TextLectureInListDto extends LeafDetailDto {
}
