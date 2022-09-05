package cc.roadmaps.extauth.bridges;

import java.util.UUID;

@FunctionalInterface
public interface UserServiceBridge {

    UUID getUserIdOrCreateIfAbsent(String email, String name);
}
