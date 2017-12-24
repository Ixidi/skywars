package pl.ixidi.hhskywars.listener;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import pl.ixidi.hhskywars.SkyWarsPlugin;
import pl.ixidi.hhskywars.basic.util.ArenaUtils;
import pl.ixidi.hhskywars.util.FileUtils;
import pl.ixidi.hhskywars.util.StringUtils;

public class WorldChangeListener implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        World world = event.getFrom();
        boolean match = ArenaUtils.getArenaMap().keySet().stream()
                .anyMatch(name -> world.getName().equalsIgnoreCase("test_" + world.getName()));
        if (match && world.getPlayers().size() == 0) {
            SkyWarsPlugin.getPlugin(SkyWarsPlugin.class).getServer().unloadWorld(world, false);
        }
        FileUtils.deleteAll(world.getWorldFolder());
        event.getPlayer().sendMessage(StringUtils.color("&aWszyscy gracze opuscili testowy swiat areny. Zostal on usuniety."));
    }

}
