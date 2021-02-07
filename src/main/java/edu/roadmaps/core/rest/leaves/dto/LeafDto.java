package edu.roadmaps.core.rest.leaves.dto;

import edu.roadmaps.core.model.entity.LeafType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LeafDto {
    private String title;
    private LeafType type;
}
