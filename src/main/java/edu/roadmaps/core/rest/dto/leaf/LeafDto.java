package edu.roadmaps.core.rest.dto.leaf;

import edu.roadmaps.core.model.entity.leaf.LeafType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class LeafDto {
    private String title;
    private LeafType type;
}
