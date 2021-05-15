package io.roadmaps.core.model.entity;

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
@SecondaryTable(name = "leaf_link")
@DiscriminatorValue(value = "LINK")
public class LinkLeaf extends Leaf {

    @Column(name = "link", table="leaf_link")
    private String link;
}


