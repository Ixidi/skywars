package pl.ixidi.hhskywars.basic;

import java.io.File;

public class ArenaFiles {

    private File root;
    private File map;
    private File arenaData;

    public ArenaFiles(File root) {
        this.root = root;
        this.map = map;
        this.arenaData = arenaData;
    }

    public File getRoot() {
        return root;
    }

    public File getMap() {
        return map;
    }

    public File getArenaData() {
        return arenaData;
    }
}
