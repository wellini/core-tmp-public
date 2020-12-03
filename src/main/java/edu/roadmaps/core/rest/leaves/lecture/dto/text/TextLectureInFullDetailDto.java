package edu.roadmaps.core.rest.leaves.lecture.dto.text;

import edu.roadmaps.core.rest.leaves.lecture.dto.LectureFullDetailLeafDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class TextLectureInFullDetailDto extends LectureFullDetailLeafDto {
    private String text;
}
