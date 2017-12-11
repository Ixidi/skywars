package pl.ixidi.hhskywars.command.executor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.data.Settings;
import pl.ixidi.hhskywars.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ListExec implements Executor {

    @Override
    public void execute(CommandSender sender, CommandHandler handler, String[] args) {
        sender.sendMessage("");
        sender.sendMessage(StringUtils.color("&8&lSkyWars &7by Ixidi"));
        CommandHandler checkerHandler = handler;
        ArrayList<String> subcommands = new ArrayList<>();
        while (checkerHandler != null) {
            subcommands.add(checkerHandler.getName());
            checkerHandler = checkerHandler.getParent();
        }
        StringBuilder builder = new StringBuilder();
        for (int i = subcommands.size() - 1; i >= 0; i--) {
            builder.append(subcommands.get(i)).append(" ");
        }
        String prefix = builder.toString();
        Map<String, CommandHandler> secondHandlers = handler.getSecondHandlers();
        Map<String, CommandHandler> avalibleHandlers;
        if (sender instanceof Player) {
            avalibleHandlers = new HashMap<>();
            secondHandlers.entrySet().stream()
                    .filter(entry -> sender.hasPermission(entry.getValue().getPermission()))
                    .forEach(entry -> avalibleHandlers.put(entry.getKey(), entry.getValue()));
        } else {
            avalibleHandlers = secondHandlers;
        }
        if (avalibleHandlers.size() == 0) {
            sender.sendMessage(Settings.getInstance().getMessages().noCommands);
            return;
        }
        sender.sendMessage(StringUtils.color(" &7-> &a/"+prefix+""));
        avalibleHandlers.forEach((name, second) -> sender.sendMessage(StringUtils.color(" &7-> &a" + name + " - "+second.getDescription())));
    }


}
