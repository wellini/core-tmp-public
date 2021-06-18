package io.roadmaps.core.model.security;

import io.roadmaps.core.model.security.enums.AuthProviderType;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(indexes = {
        @Index(columnList = "userId,type")
})
public class AuthProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private AuthProviderType type;

    private String token;

    private UUID userId;

}
