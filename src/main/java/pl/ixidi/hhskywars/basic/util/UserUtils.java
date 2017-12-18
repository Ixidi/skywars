package pl.ixidi.hhskywars.basic.util;

import pl.ixidi.hhskywars.basic.User;

import java.util.HashMap;
import java.util.UUID;

public final class UserUtils {

    private static HashMap<UUID, User> userMap = new HashMap<>();

    public static void add(User user) {
        userMap.put(user.getUuid(), user);
    }

    public static void remove(User user) {
        if (userMap.containsValue(user)) {
            userMap.remove(user.getUuid());
        }
    }

    public static User get(UUID uuid) {
        User user = userMap.get(uuid);
        return user != null ? user : new User(uuid);
    }

    public static User get(String name) {
        return userMap.values().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findAny().orElse(null);
    }
    
    private UserUtils() { }

}
