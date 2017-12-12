package pl.ixidi.hhskywars.command.executor.game;

import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.basic.Game;
import pl.ixidi.hhskywars.basic.util.GameUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.util.StringUtils;

public class GameCreateExec implements Executor {

    @Override
    public void execute(CommandSender sender, CommandHandler handler, String[] args) {
        Game game = new Game(GameUtils.getGameMap().size() + 1);
        GameUtils.add(game);
        game.startTask();
        sender.sendMessage(StringUtils.color("&aStworzono gre &7" + game.getId() + "&a."));
        if (game.getTask() == null) {
            sender.sendMessage(StringUtils.color("&cWystapil problem z arenami, task gry nie wystartowal."));
            sender.sendMessage(StringUtils.color("&cPo naprawieniu bledu uzyj komendy &7/... starttask"));
        }
    }

}
