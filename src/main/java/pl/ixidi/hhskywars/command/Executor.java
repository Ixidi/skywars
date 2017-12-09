package pl.ixidi.hhskywars.command;

import org.bukkit.command.CommandSender;

public interface Executor {

    void execute(CommandSender sender, CommandHandler handler, String[] args);

}
