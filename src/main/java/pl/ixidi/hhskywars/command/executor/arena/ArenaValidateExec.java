package pl.ixidi.hhskywars.command.executor.arena;

import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.basic.Arena;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandUtils;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.util.StringUtils;
import pl.ixidi.hhskywars.util.Validator;

public class ArenaValidateExec implements Executor {

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
        Validator.ArenaResult result = Validator.validateArena(arena);
        if (!result.isValidated()) {
            arena.setValidated(false);
            sender.sendMessage(StringUtils.color("&cArena nie zostala zweryfikowana."));
            sender.sendMessage(StringUtils.color("&cPowod: &7"+result.getCause()+"&c."));
            return;
        }
        arena.setValidated(true);
        sender.sendMessage(StringUtils.color("&aArena zostala zweryfikowana!"));
    }

}
