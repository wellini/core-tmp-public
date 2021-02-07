package edu.roadmaps.core.rest.leaves.dto;

import edu.roadmaps.core.rest.leaves.dto.LeafInUpdateDto;
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
public class LinkLectureInUpdateDto extends LeafInUpdateDto {
    private String link;
}
