package pl.ixidi.hhskywars.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.ixidi.hhskywars.basic.User;
import pl.ixidi.hhskywars.basic.util.UserUtils;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = UserUtils.get(player.getUniqueId());
        user.setName(player.getName());
    }

}
