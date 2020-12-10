package edu.roadmaps.core.rest.dto.leaf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.rest.leaves.lecture.dto.link.LinkLectureInCreateDto;
import edu.roadmaps.core.rest.leaves.lecture.dto.text.TextLectureInCreateDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LeafDto {
    private String title;
    private LeafType type;
}
