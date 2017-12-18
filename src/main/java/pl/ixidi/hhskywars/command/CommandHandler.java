package pl.ixidi.hhskywars.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ixidi.hhskywars.data.Settings;
import pl.ixidi.hhskywars.data.config.PluginMessages;
import pl.ixidi.hhskywars.util.StringUtils;

import java.util.*;

public class CommandHandler implements CommandExecutor{

    private String name;
    private String permission;
    private List<String> aliases;
    private Executor executor;
    private boolean playerOnly;
    private String description;

    private CommandHandler parent;
    private Map<String, CommandHandler> secondHandlers;

    public CommandHandler(String name, Executor executor, String permission, boolean playerOnly, List<String> aliases) {
        this.name = name;
        this.permission = permission;
        if (aliases == null || aliases.size() == 0) {
            this.aliases = new ArrayList<>();
        } else {
            this.aliases = aliases;
        }
        this.executor = executor;
        this.playerOnly = playerOnly;
        this.secondHandlers = new HashMap<>();
        this.description = "SkyWars command.";
    }

    public CommandHandler(String name, Executor executor, String permission, boolean playerOnly, String... aliases) {
        this(name, executor, permission, playerOnly, Arrays.asList(aliases));
    }

    public String getName() {
        return name;
    }

    public Executor getExecutor() {
        return executor;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void addSecondHandler(CommandHandler... handlers) {
        for (CommandHandler handler : handlers) {
            handler.parent = this;
            secondHandlers.put(handler.getName(), handler);
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, CommandHandler> getSecondHandlers() {
        return secondHandlers;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isPlayerOnly() {
        return playerOnly;
    }

    public CommandHandler getParent() {
        return parent;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        PluginMessages messages = Settings.getInstance().getMessages();
        if (this.playerOnly && !(commandSender instanceof Player)) {
            commandSender.sendMessage(messages.playerOnly);
            return true;
        }
        if (commandSender instanceof Player && !commandSender.hasPermission(this.permission)) {
            commandSender.sendMessage(messages.noPermission);
            return true;
        }
        if (this.secondHandlers.size() > 0 && args.length > 0) {
            String subcommand = args[0];
            CommandHandler secondHandler = null;
            for (CommandHandler handler : this.secondHandlers.values()) {
                if (subcommand.equalsIgnoreCase(handler.name) || handler.aliases.contains(subcommand.toLowerCase())) {
                    secondHandler = handler;
                    break;
                }
            }
            if (secondHandler != null) {
                String[] newArgs = new String[args.length - 1];
                System.arraycopy(args, 1, newArgs, 0, newArgs.length);
                return secondHandler.onCommand(commandSender, command, s, newArgs);
            }
            commandSender.sendMessage(StringUtils.replace(messages.commandNotFound, "{COMMAND}", CommandUtils.getParentsAsString(this)));
            return true;
        }
        try {
            this.executor.execute(commandSender, this, args);
        } catch (ArrayIndexOutOfBoundsException ex) {
            commandSender.sendMessage(messages.arrayException);
        }
        return true;
    }
}
