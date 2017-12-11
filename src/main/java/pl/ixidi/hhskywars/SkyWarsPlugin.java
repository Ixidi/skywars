package pl.ixidi.hhskywars;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandManager;
import pl.ixidi.hhskywars.command.executor.ListExec;
import pl.ixidi.hhskywars.command.executor.arena.ArenaCreateExec;
import pl.ixidi.hhskywars.data.Settings;
import pl.ixidi.hhskywars.util.LogUtils;

import java.io.File;
import java.io.IOException;

public class SkyWarsPlugin extends JavaPlugin {

    private CommandManager commandManager;

    @Override
    public void onEnable() {
        File mapsFolder = new File(this.getDataFolder().getPath() + File.separator + "maps");
        if (!mapsFolder.exists()) {
            if (!mapsFolder.mkdirs()) {
                LogUtils.exception(new IOException("System could not create maps folder!"));
            }
        }
        this.commandManager = new CommandManager(this);
        new Settings();
        this.listeners();
        this.commands();
    }

    @Override
    public void onDisable() {

    }


    private void commands() {
        //arena
        CommandHandler aMain = new CommandHandler("arena", new ListExec(), "skywars.admin", false);
        aMain.addSecondHandler(new CommandHandler("create", new ArenaCreateExec(), "skywars.admin", false));

        //main
        CommandHandler main = new CommandHandler("skywarsadmin", new ListExec(), "skywars.admin", false, "swa");
        main.addSecondHandler(aMain);
        this.commandManager.registerCommand(main);
    }

    private void listeners() {
        PluginManager manager = this.getServer().getPluginManager();
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
