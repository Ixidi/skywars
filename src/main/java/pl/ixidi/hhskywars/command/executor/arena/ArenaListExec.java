package pl.ixidi.hhskywars.command.executor.arena;

import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.basic.Arena;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.util.StringUtils;

import java.util.HashMap;

public class ArenaListExec implements Executor {

    @Override
    public void execute(CommandSender sender, CommandHandler handler, String[] args) {
        sender.sendMessage(StringUtils.color("&8&lLista aren:"));
        HashMap<String, Arena> map = ArenaUtils.getArenaMap();
        if (map.size() == 0) {
            sender.sendMessage(StringUtils.color(" &7-> &cBrak."));
            return;
        }
        map.forEach((name, arena) -> sender.sendMessage(StringUtils.color(" &7-> &a"+name)));
    }

}
