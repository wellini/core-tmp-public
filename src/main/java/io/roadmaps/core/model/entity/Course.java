package io.roadmaps.core.model.entity;

import io.roadmaps.core.model.entity.enums.CourseCoverTheme;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    private UUID authorId;

    @Enumerated(EnumType.STRING)
    private CourseCoverTheme coverTheme;

    private String coverUrl;
}
