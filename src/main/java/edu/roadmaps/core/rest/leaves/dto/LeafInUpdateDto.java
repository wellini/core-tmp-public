package edu.roadmaps.core.rest.leaves.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
