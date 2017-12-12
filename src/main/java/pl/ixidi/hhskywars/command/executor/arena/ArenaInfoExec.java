package pl.ixidi.hhskywars.command.executor.arena;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.basic.Arena;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandUtils;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.util.LocationUtils;
import pl.ixidi.hhskywars.util.StringUtils;

public class ArenaInfoExec implements Executor {

    @Override
    public void execute(CommandSender sender, CommandHandler handler, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(CommandUtils.getUsage(handler, "nazwa areny"));
            return;
        }
        String name = args[0];
        Arena arena = ArenaUtils.get(name);
        if (arena == null) {
            sender.sendMessage(StringUtils.color("&cArena o nazwie &7" + name + " &cnie istnieje!"));
            return;
        }
        sender.sendMessage(StringUtils.color("&8&lInformacje o arenie &7" + arena.getName() + "&8&l:\n"
                                                + " &7-> &aCzas stworzenia: &7" + arena.getCreatedTime() + "\n"
                                                + " &7-> &aLiczba graczy: &7" + arena.getMaxPlayers() + "\n"
                                                + " &7-> &aPunkty spawnow:"));
        Location[] spawns = arena.getSpawns();
        for (int i = 0; i < spawns.length; i++) {
            Location location = spawns[i];
            sender.sendMessage(StringUtils.color(" &7->  &8- &a" + (i + 1) + " " + (location != null ? "&2" + LocationUtils.toLocationString(location) + "." : "&4Nie ustawiony!")));
        }
        sender.sendMessage(StringUtils.color(" &7-> &aDopuszczona: " + (arena.isValidated() ? "&2TAK" : "&4NIE")));
    }

}
