package edu.roadmaps.core.model.entity.attachment;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    private UUID id;
    private String url;
    private Integer orderId;
}
