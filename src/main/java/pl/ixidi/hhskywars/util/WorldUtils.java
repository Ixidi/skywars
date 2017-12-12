package pl.ixidi.hhskywars.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class WorldUtils {

    public static boolean isValidWorld(File worldFolder) {
        String path = worldFolder.getPath();
        List<File> files = new ArrayList<>();
        files.add(worldFolder);
        files.add(new File(path + File.separator + "level.dat"));
        files.add(new File(path + File.separator + "region"));
        files.add(new File(path + File.separator + "data"));
        if (files.stream().anyMatch((file -> !file.exists()))) {
            return false;
        }
        return true;
    }

    private WorldUtils() { }

}
