package pl.ixidi.hhskywars.basic;

import org.bukkit.Location;
import pl.ixidi.hhskywars.SkyWarsPlugin;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.util.FileUtils;
import pl.ixidi.hhskywars.util.LogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Game {

    private final int id;
    private GameState state;
    private GameTask task;
    private File mapFolder;

    private Arena arena;
    private ArrayList<Location> freeSpawns = new ArrayList<>();

    private HashMap<Integer, User> allUsers = new HashMap<>();
    private ArrayList<User> livingUsers = new ArrayList<>();
    private ArrayList<User> spectatingUsers = new ArrayList<>();

    public Game(int id) {
        this.id = id;
        this.state = GameState.DISABLED;
        this.mapFolder = new File(SkyWarsPlugin.getPlugin(SkyWarsPlugin.class).getServer().getWorldContainer(), "game_"+id);
        if (mapFolder.exists()) {
            FileUtils.deleteAll(mapFolder);
        }
        this.mapFolder.mkdirs();
    }

    public int getId() {
        return id;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public ArrayList<Location> getFreeSpawns() {
        return freeSpawns;
    }

    public HashMap<Integer, User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<User> getLivingUsers() {
        return livingUsers;
    }

    public void setLivingUsers(ArrayList<User> livingUsers) {
        this.livingUsers = livingUsers;
    }

    public ArrayList<User> getSpectatingUsers() {
        return spectatingUsers;
    }

    public void setSpectatingUsers(ArrayList<User> spectatingUsers) {
        this.spectatingUsers = spectatingUsers;
    }

    public GameTask getTask() {
        return task;
    }

    public void startTask() {
        if (this.task != null) {
            return;
        }
        if (!prepareArena()) {
            this.state = GameState.DISABLED;
            return;
        }
        this.task = new GameTask(this);
        this.task.runTaskTimer(SkyWarsPlugin.getPlugin(SkyWarsPlugin.class), 0, 20);
        this.state = GameState.WAITING;
        LogUtils.info("Uruchomiono task gry " + this.id + ".");
    }

    public void stopTask() {
        if (this.task == null) {
            return;
        }
        this.task.cancel();
        this.task = null;
        this.arena = null;
        this.state = GameState.DISABLED;
        FileUtils.deleteAll(this.mapFolder);
    }

    boolean prepareArena() {
        this.state = GameState.PREPARING;
        Arena randomArena = ArenaUtils.getRandom();
        if (randomArena == null) {
            LogUtils.error("Task gry " + this.id + " nie mogl wystartowac. Brak jakichkolwiek aren.");
            return false;
        }
        if (!randomArena.isValidated()) {
            LogUtils.warning("Arena " + randomArena.getName() + " zostala wylosowana przez gre " + this.id + ", ale nie jest zweryfikowana.");
            for (Arena checkArena : ArenaUtils.getArenaMap().values()) {
                if (checkArena.isValidated()) {
                    this.arena = checkArena;
                    break;
                }
            }
            if (this.arena == null) {
                LogUtils.error("Task gry " + this.id + " nie mogl wystartowac. Brak zweryfikowanych aren.");
                return false;
            }
            LogUtils.warning("Arena dla gry " + this.id + " zostala zmieniona na " + this.arena.getName()+".");
        } else {
            this.arena = randomArena;
        }
        this.freeSpawns.addAll(Arrays.asList(this.arena.getSpawns()));
        return true;
    }
}
