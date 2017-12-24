package pl.ixidi.hhskywars.command.executor.arena;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ixidi.hhskywars.SkyWarsPlugin;
import pl.ixidi.hhskywars.basic.Arena;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandUtils;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.util.FileUtils;
import pl.ixidi.hhskywars.util.LogUtils;
import pl.ixidi.hhskywars.util.StringUtils;
import pl.ixidi.hhskywars.util.WorldUtils;

import java.io.IOException;


public class ArenaTestworldExec implements Executor {

    @Override
    public void execute(CommandSender sender, CommandHandler handler, String[] args) {
        Player player = (Player) sender;
        if (args.length < 1) {
            sender.sendMessage(CommandUtils.getUsage(handler, "nazwa areny"));
            return;
        }
        String name = args[0];
        Arena arena = ArenaUtils.get(name);
        if (arena == null) {
            sender.sendMessage(StringUtils.color("&cArena o nazwie &7" + name + " &cnie istnieje!"));
            return;
        }
        if (!WorldUtils.isValidWorld(arena.getMap(), false)) {
            sender.sendMessage(StringUtils.color("&cFolder mapy dla tej areny nie jest prawidlowy."));
            return;
        }
        Arena clonedArena;
        try {
            clonedArena = (Arena) arena.clone();
        } catch (CloneNotSupportedException ex) {
            LogUtils.exception(ex);
            return;
        }
        SkyWarsPlugin plugin = SkyWarsPlugin.getPlugin(SkyWarsPlugin.class);
        String mapName = "test_" + clonedArena.getName();
        World testWorld = plugin.getServer().getWorld(mapName);
        if (testWorld != null) {
            sender.sendMessage(StringUtils.color("&aTestowy swiat dla tej areny juz istnieje!\n" +
                            "&aZostaniesz na nia automatycznie przeniesiony...\n" +
                            "&cPamietaj, ze testowa mapa zniknie, jesli wszyscy ja opuszcza."));
            player.teleport(testWorld.getSpawnLocation());
            return;
        }
        try {
            WorldUtils.copyWorld(clonedArena, mapName);
        } catch (IOException ex) {
            sender.sendMessage(StringUtils.color("&cWystapil blad podczas kopiowania mapy!\n&cPowod: &7" + ex.getMessage() + "&c."));
            return;
        }
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            World world = WorldUtils.loadWorld(mapName);
            if (world == null) {
                sender.sendMessage(StringUtils.color("&cNie mozna zaladowac swiata! Sprawdz logi w konsoli."));
                return;
            }
            player.teleport(world.getSpawnLocation());
            sender.sendMessage(StringUtils.color("&aStworzono testowy swiat areny &7" + arena.getName() + "&a.\n" +
                    "&cPamietaj, ze testowa mapa zniknie, jesli wszyscy ja opuszcza."));
        }, 10);
    }

}
