package pl.ixidi.hhskywars.basic;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.SkyWarsPlugin;
import pl.ixidi.hhskywars.data.DataManager;
import pl.ixidi.hhskywars.util.FileUtils;

import java.io.File;

public class Arena implements Cloneable {

    private String name;

    private File map;

    private int maxPlayers;
    private Location[] spawns;
    private boolean validated;

    public Arena(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.spawns = new Location[maxPlayers];
        this.validated = false;
        File root = new File(DataManager.ARENAS_FOLDER, name);
        this.map = new File(root, "map");
        FileUtils.createFolder(this.map);
    }

    public String getName() {
        return name;
    }

    public Location[] getSpawns() {
        return spawns;
    }

    public int getMaxPlayers() {
        return maxPlayers;
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

    public File getMap() {
        return map;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
