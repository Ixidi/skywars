package pl.ixidi.hhskywars.util;

import org.bukkit.Location;
import pl.ixidi.hhskywars.basic.Arena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Validator {

    public static ArenaResult validateArena(Arena arena) {
        StringBuilder builder = new StringBuilder();
        boolean validated = true;
        if (!WorldUtils.isValidWorld(arena.getFiles().getMap())) {
            builder.append("Bledne pliki mapy. ");
            validated = false;
        }
        List<Integer> unsetSpawns = new ArrayList<>();
        Location[] spawns = arena.getSpawns();
        for (int i = 0; i < spawns.length; i++) {
            if (spawns[i] == null) {
                unsetSpawns.add(i + 1);
            }
        }
        if (unsetSpawns.size() > 0) {
            if (validated) {
                validated = false;
            }
            builder.append("Nie ustawione punkty spawnow ");
            for (int i = 0; i < unsetSpawns.size(); i++) {
                builder.append(unsetSpawns.get(i));
                if (i != unsetSpawns.size()) {
                    builder.append(", ");
                }else {
                    builder.append(".");
                }
            }
        }
        return new ArenaResult(validated, builder.toString());
    }

    private Validator() { }

    public static class ArenaResult {

        private boolean validated;
        private String cause;

        public ArenaResult(boolean validated, String cause) {
            this.validated = validated;
            this.cause = cause;
        }

        public boolean isValidated() {
            return validated;
        }

        public String getCause() {
            return cause;
        }

    }

}
