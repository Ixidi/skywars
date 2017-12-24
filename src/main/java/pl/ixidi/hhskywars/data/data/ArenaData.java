package pl.ixidi.hhskywars.data.data;

import org.apache.commons.io.FileUtils;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import pl.ixidi.hhskywars.SkyWarsPlugin;
import pl.ixidi.hhskywars.basic.Arena;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.util.IntegerUtils;
import pl.ixidi.hhskywars.util.LocationUtils;
import pl.ixidi.hhskywars.util.YamlFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArenaData {

    private final File arenasFolder;

    public ArenaData(File arenasFolder) {
        this.arenasFolder = arenasFolder;
    }

    public List<Arena> loadArenas() {
        List<Arena> arenas = new ArrayList<>();
        File[] folders = arenasFolder.listFiles();
        if (folders == null) {
            return arenas;
        }
        for (File folder : folders) {
            if (!folder.isDirectory()) {
                continue;
            }
            boolean broken = false;
            File file = new File(folder, "arenaData.yml");
            if (file.exists()) {
                YamlFile yamlFile = new YamlFile(file);
                int max = yamlFile.getInt("max");
                if (max < 2) {
                    max = 2;
                }
                Arena arena = new Arena(folder.getName(), max);
                System.out.println(yamlFile + " " + file.getName());
                ConfigurationSection section = yamlFile.getConfigurationSection("spawns");
                if (section != null) {
                    Set<String> keys = section.getKeys(false);
                    if (keys.size() > 0) {
                        for (String key : keys) {
                            Integer num = IntegerUtils.toNoNegativeInteger(key);
                            if (num == null) {
                                broken = true;
                                break;
                            }
                            if (num > max - 1) {
                                broken = true;
                                break;
                            }
                            Location spawn = LocationUtils.toLcation(yamlFile.getString("spawns." + key), SkyWarsPlugin.getPlugin(SkyWarsPlugin.class).getServer().getWorlds().get(0));
                            if (spawn == null) {
                                broken = true;
                                break;
                            }
                            arena.setSpawn(spawn, num);
                        }
                    }
                }
                if (!broken) {
                    arenas.add(arena);
                }
            } else {
                broken = true;
            }
            if (broken) {
                file.renameTo(new File(arenasFolder, "BROKEN_" + System.nanoTime() + ".yml"));
            }
        }
        return arenas;
    }

    public void saveArena(Arena arena) {
        YamlFile yamlFile = new YamlFile(new File(this.arenasFolder, arena.getName() + File.separator + "arenaData.yml"));
        yamlFile.set("max", arena.getMaxPlayers());
        System.out.print(yamlFile);
        for (int i = 0; i < arena.getSpawns().length; i++) {
            Location loc = arena.getSpawns()[i];
            if (loc == null) {
                continue;
            }
            yamlFile.set("spawns." + String.valueOf(i), LocationUtils.toString(loc));
        }
        yamlFile.save();
    }
}
