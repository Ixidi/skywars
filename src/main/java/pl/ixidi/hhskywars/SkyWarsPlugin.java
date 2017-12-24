package pl.ixidi.hhskywars;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import pl.ixidi.hhskywars.basic.Arena;
import pl.ixidi.hhskywars.basic.Game;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.basic.util.GameUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandManager;
import pl.ixidi.hhskywars.command.executor.ListExec;
import pl.ixidi.hhskywars.command.executor.UserInfoExec;
import pl.ixidi.hhskywars.command.executor.arena.*;
import pl.ixidi.hhskywars.command.executor.game.GameCreateExec;
import pl.ixidi.hhskywars.command.executor.game.GameListExec;
import pl.ixidi.hhskywars.data.DataManager;
import pl.ixidi.hhskywars.data.Settings;
import pl.ixidi.hhskywars.listener.PlayerQuitListener;
import pl.ixidi.hhskywars.listener.PreLoginListener;
import pl.ixidi.hhskywars.listener.WorldChangeListener;
import pl.ixidi.hhskywars.util.Validator;
import pl.ixidi.hhskywars.util.YamlFile;

import java.io.File;

public class SkyWarsPlugin extends JavaPlugin {

    private CommandManager commandManager;

    @Override
    public void onEnable() {
        this.commandManager = new CommandManager(this);
        Settings settings = new Settings();

        DataManager dataManager = new DataManager();

        this.listeners();
        this.commands();

        dataManager.getArenaData().loadArenas().forEach(ArenaUtils::add);

        ArenaUtils.getArenaMap().values().stream()
                .filter(arena -> Validator.validateArena(arena).isValidated())
                .forEach(arena -> arena.setValidated(true));

        for (int i = 0; i < settings.getConfig().gamesCount; i++) {
            Game game = new Game(GameUtils.getGameMap().size() + 1);
            game.startTask();
            GameUtils.add(game);
        }
    }

    @Override
    public void onDisable() {
        ArenaUtils.getArenaMap().forEach((name, arena) -> {
            DataManager.getInstance().getArenaData().saveArena(arena);
        });
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
        CommandHandler aTestworld = new CommandHandler("testworld", new ArenaTestworldExec(), "skywars.admin", true);
        aTestworld.setDescription("Tworzy testowy swiat dla danej areny.");

        aMain.addSecondHandler(aCreate, aList, aInfo, aVaidate, aSetspawn, aTestworld);

        //GAME
        CommandHandler gMain = new CommandHandler("game", new ListExec(), "skywars.admin", false);

        CommandHandler gCreate = new CommandHandler("create", new GameCreateExec(), "skywars.admin", false);
        gCreate.setDescription("Tworzy gre.");
        CommandHandler gList = new CommandHandler("list", new GameListExec(), "skywars.admin", false);
        gList.setDescription("Lista gier.");

        gMain.addSecondHandler(gCreate, gList);

        //MAIN
        CommandHandler main = new CommandHandler("skywarsadmin", new ListExec(), "skywars.admin", false, "swa");

        CommandHandler infoM = new CommandHandler("userinfo", new UserInfoExec(), "skywars.admin", false);

        main.addSecondHandler(infoM, aMain, gMain);
        this.commandManager.registerCommand(main);
    }

    private void listeners() {
        PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents(new PreLoginListener(), this);
        manager.registerEvents(new PlayerQuitListener(), this);
        manager.registerEvents(new PreLoginListener(), this);
        manager.registerEvents(new WorldChangeListener(), this);
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
