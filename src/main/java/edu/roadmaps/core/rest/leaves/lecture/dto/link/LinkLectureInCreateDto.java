package edu.roadmaps.core.rest.leaves.lecture.dto.link;

import edu.roadmaps.core.rest.dto.leaf.LeafDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LinkLectureInCreateDto extends LeafDto {
    private String link;
    private Integer orderId;
}
