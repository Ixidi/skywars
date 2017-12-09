package pl.ixidi.hhskywars.command;

import org.bukkit.command.CommandMap;
import pl.ixidi.hhskywars.SkyWarsPlugin;
import pl.ixidi.hhskywars.util.LogUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private SkyWarsPlugin plugin;
    private CommandMap commandMap;

    private Map<String, CommandHandler> commands;

    public CommandManager(SkyWarsPlugin plugin) {
        this.plugin = plugin;
        this.commands = new HashMap<>();
        this.commandMap();
    }

    private void commandMap() {
        try {
            Field field = plugin.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            this.commandMap = (CommandMap) field.get(plugin.getServer());
        } catch (Exception ex) {
            LogUtils.exception(ex);
        }
    }

    public void registerCommand(CommandHandler handler) {
        PreparedCommand prepared = new PreparedCommand(handler);
        prepared.setDescription(handler.getDescription());
        if (handler.getAliases().size() > 0) {
            prepared.setAliases(handler.getAliases());
        }
        this.commandMap.register("", prepared);
        this.commands.put(handler.getName(), handler);
    }

    public void registerCommands(CommandHandler... handlers) {
        Arrays.asList(handlers).forEach(this::registerCommand);
    }

    public Map<String, CommandHandler> getCommands() {
        return commands;
    }
}
