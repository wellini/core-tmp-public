package edu.roadmaps.core.rest.dto.leaf;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import edu.roadmaps.core.rest.leaves.lecture.dto.link.LinkLectureInCreateDto;
import edu.roadmaps.core.rest.leaves.lecture.dto.link.LinkLectureInUpdateDto;
import edu.roadmaps.core.rest.leaves.lecture.dto.text.TextLectureInCreateDto;
import edu.roadmaps.core.rest.leaves.lecture.dto.text.TextLectureInUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LinkLectureInUpdateDto.class, name = "LINK_LECTURE"),
        @JsonSubTypes.Type(value = TextLectureInUpdateDto.class, name = "TEXT_LECTURE")
})
public class LeafInUpdateDto extends LeafDto{
}
