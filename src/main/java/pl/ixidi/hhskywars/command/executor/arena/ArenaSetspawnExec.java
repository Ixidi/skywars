package pl.ixidi.hhskywars.command.executor.arena;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ixidi.hhskywars.basic.Arena;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandUtils;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.util.IntegerUtils;
import pl.ixidi.hhskywars.util.LocationUtils;
import pl.ixidi.hhskywars.util.StringUtils;

public class ArenaSetspawnExec implements Executor {

    @Override
    public void execute(CommandSender sender, CommandHandler handler, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(CommandUtils.getUsage(handler, "nazwa areny", "numer spawnu"));
            return;
        }
        String name = args[0];
        Arena arena = ArenaUtils.get(name);
        if (arena == null) {
            sender.sendMessage(StringUtils.color("&cArena o nazwie &7" + name + " &cnie istnieje!"));
            return;
        }
        Integer spawnNr = IntegerUtils.toPositiveInteger(args[1]);
        if (spawnNr == null) {
            sender.sendMessage(StringUtils.color("&cNumer spawnu musi byc dodatnia liczba."));
            return;
        }
        Location[] spawns = arena.getSpawns();
        if (spawnNr > spawns.length) {
            sender.sendMessage(StringUtils.color("&cNa ta arene przewidziane jest/sa &7" + spawns.length + " &cspawnow/y."));
            return;
        }
        Location loc = ((Player) sender).getLocation();
        arena.setSpawn(loc, spawnNr - 1);
        sender.sendMessage(StringUtils.color("&aUstawiono polozenia spawnu &7" + spawnNr + " &ana &7" + LocationUtils.toLocationString(loc) + "&a."));
    }

}
