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
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
public class Leaf {
    @Id
    private UUID id;
    private String title;
    private LeafType type;
    private Integer orderId;
    @OneToMany(mappedBy = "leaf",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Attachment> attachments;

}
