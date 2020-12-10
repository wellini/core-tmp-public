package edu.roadmaps.core.model.entity.attachment;

import edu.roadmaps.core.model.entity.leaf.Leaf;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "url")
    private String url;
    @Column(name = "order_id")
    private Integer orderId;

//    @ManyToOne(targetEntity = Leaf.class)
//    private Leaf parent;
}
