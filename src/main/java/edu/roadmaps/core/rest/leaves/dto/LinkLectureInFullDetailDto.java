package edu.roadmaps.core.rest.leaves.dto;

import edu.roadmaps.core.rest.leaves.dto.LectureFullDetailLeafDto;
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
public class LinkLectureInFullDetailDto extends LectureFullDetailLeafDto {
    private String link;
}
