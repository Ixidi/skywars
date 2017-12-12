package pl.ixidi.hhskywars.basic.util;

import pl.ixidi.hhskywars.basic.Arena;

import java.util.HashMap;
import java.util.Random;

public final class ArenaUtils {

    private static Random random = new Random();
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

    public static Arena getRandom() {
        if (arenaMap.size() == 0) {
            return null;
        }
        int index = random.nextInt(arenaMap.size());
        return (Arena) arenaMap.values().toArray()[index];
    }

    public static HashMap<String, Arena> getArenaMap() {
        return arenaMap;
    }

    private ArenaUtils() { }
}
