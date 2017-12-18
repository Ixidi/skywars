package pl.ixidi.hhskywars.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.ixidi.hhskywars.basic.User;
import pl.ixidi.hhskywars.basic.util.UserUtils;
import pl.ixidi.hhskywars.data.DataManager;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = UserUtils.get(player.getUniqueId());
        user.setName(player.getName());
        DataManager.getInstance().getUserData().saveUser(user);
        UserUtils.remove(user);
    }

}
