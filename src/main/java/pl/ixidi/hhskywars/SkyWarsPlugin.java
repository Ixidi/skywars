package pl.ixidi.hhskywars;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import pl.ixidi.hhskywars.basic.Arena;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandManager;
import pl.ixidi.hhskywars.command.executor.ListExec;
import pl.ixidi.hhskywars.command.executor.arena.*;
import pl.ixidi.hhskywars.command.executor.game.GameCreateExec;
import pl.ixidi.hhskywars.command.executor.game.GameListExec;
import pl.ixidi.hhskywars.data.Settings;
import pl.ixidi.hhskywars.util.Validator;

import java.io.File;

public class SkyWarsPlugin extends JavaPlugin {

    private CommandManager commandManager;

    @Override
    public void onEnable() {
        File mapsFolder = new File(this.getDataFolder().getPath(), "maps");
        if (!mapsFolder.exists()) {
            mapsFolder.mkdirs();
        }
        this.commandManager = new CommandManager(this);
        new Settings();
        this.listeners();
        this.commands();
        ArenaUtils.getArenaMap().values().stream()
                .filter(arena -> Validator.validateArena(arena).isValidated())
                .forEach(arena -> arena.setValidated(true));
    }

    @Override
    public void onDisable() {

    }


    private void commands() {
        //ARENA
        CommandHandler aMain = new CommandHandler("arena", new ListExec(), "skywars.admin", false);

        CommandHandler aCreate = new CommandHandler("create", new ArenaCreateExec(), "skywars.admin", false);
        aCreate.setDescription("Tworzy arene.");
        CommandHandler aList = new CommandHandler("list", new ArenaListExec(), "skywars.admin", false);
        aList.setDescription("Lista aren.");
        CommandHandler aInfo = new CommandHandler("info", new ArenaInfoExec(), "skywars.admin", false);
        aInfo.setDescription("Informacje o arenie.");
        CommandHandler aVaidate = new CommandHandler("validate", new ArenaValidateExec(), "skywars.admin", false);
        aVaidate.setDescription("Weryfikuje arene.");
        CommandHandler aSetspawn = new CommandHandler("setspawn", new ArenaSetspawnExec(), "skywars.admin", true);
        aSetspawn.setDescription("Ustawia jeden ze spawnow areny.");

        aMain.addSecondHandler(aCreate, aList, aInfo, aVaidate, aSetspawn);

        //GAME
        CommandHandler gMain = new CommandHandler("game", new ListExec(), "skywars.admin", false);

        CommandHandler gCreate = new CommandHandler("create", new GameCreateExec(), "skywars.admin", false);
        gCreate.setDescription("Tworzy gre.");
        CommandHandler gList = new CommandHandler("list", new GameListExec(), "skywars.admin", false);
        gList.setDescription("Lista gier.");

        gMain.addSecondHandler(gCreate, gList);

        //MAIN
        CommandHandler main = new CommandHandler("skywarsadmin", new ListExec(), "skywars.admin", false, "swa");
        main.addSecondHandler(aMain, gMain);
        this.commandManager.registerCommand(main);
    }

    private void listeners() {
        PluginManager manager = this.getServer().getPluginManager();
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
