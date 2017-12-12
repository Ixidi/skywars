package pl.ixidi.hhskywars.command.executor.arena;

import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.basic.Arena;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandUtils;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.util.IntegerUtils;
import pl.ixidi.hhskywars.util.StringUtils;

public class ArenaCreateExec implements Executor {

    @Override
    public void execute(CommandSender sender, CommandHandler handler, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(CommandUtils.getUsage(handler, "nazwa", "maxymalna liczba graczy"));
            return;
        }
        String name = args[0];
        Integer maxPlayers = IntegerUtils.toPositiveInteger(args[1]);
        if (maxPlayers == null) {
            sender.sendMessage(StringUtils.color("&cMaxymalna liczba graczy musi byc dodatnia liczba."));
            return;
        }
        if (ArenaUtils.get(name) != null) {
            sender.sendMessage(StringUtils.color("&cArena o takiej nazwie juz istnieje."));
            return;
        }
        Arena arena = new Arena(name, maxPlayers);
        ArenaUtils.add(arena);
        sender.sendMessage(StringUtils.color("&aStworzono arene o nazwie " + name + "&a! \n"
                                                + "&aWrzuc teraz pliki mapy do &7" + arena.getFiles().getMap().getPath() + "&a, a nastepnie ustaw wszystkie punkty spawnu mapy. \n"
                                                + "&aPo tych czynnosciach system musi dopuscic arene. Uzyj &7/... validate&a."));
    }

}
