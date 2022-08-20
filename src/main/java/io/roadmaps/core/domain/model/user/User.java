package io.roadmaps.core.domain.model.user;

import io.roadmaps.core.domain.common.id.Generator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class User {

    @EqualsAndHashCode.Include
    private Long id;

    private String fullname;

    private String username;

    private User(Generator<Long> idGenerator, String username, String fullname) {
        this.id = idGenerator.generateNext();
        this.fullname = fullname;
        this.username = username;
    }

    public static User create(Generator<Long> idGenerator, String username, String fullname) {
        return new User(idGenerator, username, fullname);
    }
}
