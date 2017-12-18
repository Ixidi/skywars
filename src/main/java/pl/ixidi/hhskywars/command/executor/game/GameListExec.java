package pl.ixidi.hhskywars.command.executor.game;

import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.basic.Game;
import pl.ixidi.hhskywars.basic.GameState;
import pl.ixidi.hhskywars.basic.util.GameUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.util.StringUtils;

import java.util.HashMap;

public class GameListExec implements Executor {

    @Override
    public void execute(CommandSender sender, CommandHandler handler, String[] args) {
        sender.sendMessage(StringUtils.color("&8&lLista gier:"));
        HashMap<Integer, Game> map = GameUtils.getGameMap();
        if (map.size() == 0) {
            sender.sendMessage(StringUtils.color(" &7-> &cBrak."));
            return;
        }
        map.forEach((id, game) -> {
            StringBuilder builder = new StringBuilder(" &7-> &a");
            builder.append(id).append(" ");
            GameState state = game.getState();
            builder.append("Status: ").append(state.getStatus()).append(" ");
            if (state != GameState.DISABLED && state != GameState.PREPARING) {
                builder.append("&aGracze: &7").append(game.getAllUsers().size()).append(" ");
                builder.append("&aArena: &7").append(game.getArena().getName());
            }
            sender.sendMessage(StringUtils.color(builder.toString()));
        });
    }

}
