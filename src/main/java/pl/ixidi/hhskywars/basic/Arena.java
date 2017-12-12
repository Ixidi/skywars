package pl.ixidi.hhskywars.basic;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.SkyWarsPlugin;

import java.io.File;

public class Arena {

    private String name;
    private ArenaFiles files;
    private long createdTime;

    private int maxPlayers;
    private Location[] spawns;
    private boolean validated;

    public Arena(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        File root = new File(SkyWarsPlugin.getPlugin(SkyWarsPlugin.class).getDataFolder().getPath() + File.separator + "maps" + File.separator + name);
        this.files = new ArenaFiles(root);
        this.spawns = new Location[maxPlayers];
        this.createdTime = System.currentTimeMillis();
        this.validated = false;
    }

    public String getName() {
        return name;
    }

    public ArenaFiles getFiles() {
        return files;
    }

    public Location[] getSpawns() {
        return spawns;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public boolean setSpawn(Location location, int number) {
        if (this.spawns.length >= number) {
            this.spawns[number] = location;
            return true;
        }
        return false;
    }
}
