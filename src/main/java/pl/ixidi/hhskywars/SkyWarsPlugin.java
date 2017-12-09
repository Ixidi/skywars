package pl.ixidi.hhskywars;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandManager;
import pl.ixidi.hhskywars.command.executor.ListExecutor;

public class SkyWarsPlugin extends JavaPlugin {

    private CommandManager commandManager;

    @Override
    public void onEnable() {
        this.commandManager = new CommandManager(this);
    }

    @Override
    public void onDisable() {
        this.listeners();
        this.commands();
    }


    private void commands() {
        CommandHandler main = new CommandHandler("skywarsadmin", new ListExecutor(), "skywars.admin", false, "swa");
        this.commandManager.registerCommand(main);
    }

    private void listeners() {
        PluginManager manager = this.getServer().getPluginManager();
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
