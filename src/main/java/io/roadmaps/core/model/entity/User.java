package io.roadmaps.core.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

    public static final UUID DEFAULT_USER_ID = UUID.fromString("3950aca1-63d4-4573-a452-d00104e5e7fd");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String fullname;

    private String username;
}
