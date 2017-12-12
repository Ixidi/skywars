package pl.ixidi.hhskywars.command.executor.game;

import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.basic.Game;
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
        map.forEach((id, arena) -> sender.sendMessage(StringUtils.color(" &7-> &a"+id)));
    }

}
