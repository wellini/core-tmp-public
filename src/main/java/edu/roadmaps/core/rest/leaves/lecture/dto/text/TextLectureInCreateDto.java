package edu.roadmaps.core.rest.leaves.lecture.dto.text;

import edu.roadmaps.core.rest.dto.leaf.LeafDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class TextLectureInCreateDto extends LeafDto {
    private String text;
    private Integer orderId;
}
