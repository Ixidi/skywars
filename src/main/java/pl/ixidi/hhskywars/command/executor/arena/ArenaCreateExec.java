package pl.ixidi.hhskywars.command.executor.arena;

import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandUtils;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.data.Settings;
import pl.ixidi.hhskywars.data.config.PluginMessages;

public class ArenaCreateExec implements Executor {

    @Override
    public void execute(CommandSender sender, CommandHandler handler, String[] args) {
        PluginMessages messages = Settings.getInstance().getMessages();
        if (args.length < 2) {
            sender.sendMessage(CommandUtils.getUsage(handler, "nazwa", "maxymalna liczba graczy"));
            return;
        }
    }

}
