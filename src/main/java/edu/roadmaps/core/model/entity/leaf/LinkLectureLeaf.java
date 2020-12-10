package edu.roadmaps.core.model.entity.leaf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;


@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SecondaryTable(name = "link_lecture")
@DiscriminatorValue(value = "LINK_LECTURE")
public class LinkLectureLeaf extends Leaf {
    @Column(name = "link", table="link_lecture")
    private String link;

}


