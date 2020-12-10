package edu.roadmaps.core.model.entity.leaf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SecondaryTable(name = "text_lecture")
@DiscriminatorValue(value = "TEXT_LECTURE")
public class TextLectureLeaf extends Leaf {
    @Lob
    @Column(name = "text", table="text_lecture")
    private String text;

}
