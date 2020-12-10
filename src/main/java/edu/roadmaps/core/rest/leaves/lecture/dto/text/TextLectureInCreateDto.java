package edu.roadmaps.core.rest.leaves.lecture.dto.text;

import edu.roadmaps.core.rest.dto.leaf.LeafDto;
import edu.roadmaps.core.rest.dto.leaf.LeafInCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TextLectureInCreateDto extends LeafInCreateDto {
    private String text;
    private Integer orderId;
}
