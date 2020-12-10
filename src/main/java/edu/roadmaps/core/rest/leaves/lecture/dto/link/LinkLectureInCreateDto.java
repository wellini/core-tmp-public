package edu.roadmaps.core.rest.leaves.lecture.dto.link;

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
public class LinkLectureInCreateDto extends LeafInCreateDto {
    private String link;
    private Integer orderId;
}
