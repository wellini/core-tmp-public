package edu.roadmaps.core.rest.leaves.lecture.dto.link;

import edu.roadmaps.core.rest.leaves.lecture.dto.LectureFullDetailLeafDto;
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
public class LinkLectureInFullDetailDto extends LectureFullDetailLeafDto {
    private String link;
}
