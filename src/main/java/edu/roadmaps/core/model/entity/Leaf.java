package edu.roadmaps.core.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Inheritance
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "leaf")
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
public class Leaf {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    private LeafType type;

    @Column(name = "order_id")
    private Integer orderId;
}
