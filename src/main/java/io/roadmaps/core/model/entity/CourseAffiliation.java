package io.roadmaps.core.model.entity;

import io.roadmaps.core.model.entity.enums.CourseAffiliationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseAffiliation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID courseId;

    private UUID userId;

    @Enumerated(EnumType.STRING)
    private CourseAffiliationType type;
}
