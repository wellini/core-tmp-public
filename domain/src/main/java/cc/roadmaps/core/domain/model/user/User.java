package cc.roadmaps.core.domain.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class User {

    @EqualsAndHashCode.Include
    private UUID id;

    private String fullname;

    private String username;

    private User(String username, String fullname) {
        this.id = UUID.randomUUID();
        this.fullname = fullname;
        this.username = username;
    }

    public static User create(String username, String fullname) {
        return new User(username, fullname);
    }
}
