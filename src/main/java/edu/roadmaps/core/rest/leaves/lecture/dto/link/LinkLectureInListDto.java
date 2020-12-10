package edu.roadmaps.core.rest.leaves.lecture.dto.link;

import edu.roadmaps.core.rest.dto.leaf.LeafDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
public class LinkLectureInListDto extends LeafDetailDto {
}

