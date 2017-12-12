package pl.ixidi.hhskywars.basic;

import org.bukkit.scheduler.BukkitRunnable;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;

public class GameTask extends BukkitRunnable {

    private Game game;

    public GameTask(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        switch (game.getState()) {
            case WAITING: {

            }
        }
    }

}
