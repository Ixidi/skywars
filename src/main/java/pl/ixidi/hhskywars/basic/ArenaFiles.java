package pl.ixidi.hhskywars.basic;

import pl.ixidi.hhskywars.util.FileUtils;

import java.io.File;

public class ArenaFiles {

    private File root;
    private File map;
    private File arenaData;

    public ArenaFiles(File root) {
        this.root = root;
        String path = root.getPath();
        this.map = new File(path + File.separator, "mapFiles");
        this.arenaData = new File(path + File.separator + "arenaData.yml");
        map.mkdirs();
        FileUtils.createIfNotExists(arenaData);
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
