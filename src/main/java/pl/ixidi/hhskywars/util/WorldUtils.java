package pl.ixidi.hhskywars.util;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import pl.ixidi.hhskywars.SkyWarsPlugin;
import pl.ixidi.hhskywars.basic.Arena;
import pl.ixidi.hhskywars.basic.Game;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class WorldUtils {

    public static boolean isValidWorld(File worldFolder, boolean cleanFolder) {
        String path = worldFolder.getPath();
        List<File> required = new ArrayList<>();
        required.add(worldFolder);
        required.add(new File(path + File.separator + "level.dat"));
        required.add(new File(path + File.separator + "region"));
        required.add(new File(path + File.separator + "data"));
        if (required.stream().anyMatch((file -> !file.exists()))) {
            return false;
        }
        if (cleanFolder) {
            File[] allFiles = worldFolder.listFiles();
            if (allFiles == null) {
                return false;
            }
            Arrays.asList(allFiles).forEach(file -> {
                if (!required.contains(file)) {
                    pl.ixidi.hhskywars.util.FileUtils.deleteAll(file);
                }
            });
        }
        return true;
    }

    public static void copyWorld(Arena arena, String name) throws IOException {
        File map = arena.getMap();
        File dest = new File(SkyWarsPlugin.getPlugin(SkyWarsPlugin.class).getServer().getWorldContainer(), name);
        if (dest.exists()) {
            FileUtils.deleteQuietly(dest);
        }
        File[] files = map.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            File to = new File(dest, file.getName());
            if (file.isDirectory()) {
                FileUtils.copyDirectory(file, to);
            } else {
                FileUtils.copyFile(file, to);
            }
        }
    }

    public static World loadWorld(String name) {
        WorldCreator creator = new WorldCreator(name)
                .environment(World.Environment.NORMAL)
                .generateStructures(false);
        World world = Bukkit.createWorld(creator);
        if (world == null) {
            return null;
        }
        world.setAutoSave(false);
        world.setPVP(true);
        world.setSpawnFlags(false, false);
        return world;
    }

    private WorldUtils() { }

}
