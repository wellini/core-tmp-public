package edu.roadmaps.core.model.entity.leaf;


import edu.roadmaps.core.model.entity.attachment.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Inheritance
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
public class Leaf {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "title")
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "type",insertable=false, updatable=false)
    private LeafType type;
    @Column(name = "order_id")
    private Integer orderId;
//    @OneToMany(mappedBy = "parent",
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL
//    )
//    private List<Attachment> attachments;

}
