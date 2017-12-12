package pl.ixidi.hhskywars.basic.util;

import pl.ixidi.hhskywars.basic.Arena;

import java.util.HashMap;

public final class ArenaUtils {

    private static HashMap<String, Arena> arenaMap = new HashMap<>();

    public static void add(Arena arena) {
        arenaMap.put(arena.getName().toLowerCase(), arena);
    }

    public static void remove(Arena arena) {
        if (arenaMap.containsValue(arena)) {
            arenaMap.remove(arena.getName().toLowerCase());
        }
    }

    public static Arena get(String name) {
        return arenaMap.get(name.toLowerCase());
    }

    public static HashMap<String, Arena> getArenaMap() {
        return arenaMap;
    }

    private ArenaUtils() { }
}
