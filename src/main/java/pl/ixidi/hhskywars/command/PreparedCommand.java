package pl.ixidi.hhskywars.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

class PreparedCommand extends Command {

    private CommandHandler handler;

    public PreparedCommand(CommandHandler handler) {
        super(handler.getName());
        this.handler = handler;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return this.handler.onCommand(commandSender, this, s, strings);
    }

}
