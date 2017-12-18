package pl.ixidi.hhskywars.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import pl.ixidi.hhskywars.basic.User;
import pl.ixidi.hhskywars.basic.util.UserUtils;
import pl.ixidi.hhskywars.data.DataManager;
import pl.ixidi.hhskywars.data.Settings;

import java.sql.SQLException;

public class PreLoginListener implements Listener {

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        User user = new User(event.getUniqueId());
        try {
            user = DataManager.getInstance().getUserData().loadUser(event.getUniqueId());
        } catch (SQLException ex) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Settings.getInstance().getMessages().mysqlErrorOnJoin);
        }
        UserUtils.add(user);
    }

}
