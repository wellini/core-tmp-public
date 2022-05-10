package io.roadmaps.core.model.entity;

import io.roadmaps.core.model.entity.enums.CourseCoverTheme;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class CourseWithAuthorFullnameView {
    @Id
    private UUID id;

    private String title;

    private UUID authorId;

    @Enumerated(EnumType.STRING)
    private CourseCoverTheme coverTheme;

    private String coverUrl;

    private String fullname;
}
