package pl.ixidi.hhskywars.data.data;

import pl.ixidi.hhskywars.basic.User;
import pl.ixidi.hhskywars.data.MySQL;
import pl.ixidi.hhskywars.data.Settings;
import pl.ixidi.hhskywars.util.LogUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserData {

    private MySQL mySQLSkyWars;
    private MySQL mySQLCoins;

    public UserData(MySQL mySQLSkyWars, MySQL mySQLCoins) {
        this.mySQLSkyWars = mySQLSkyWars;
        this.mySQLCoins = mySQLCoins;
    }

    public User loadUser(UUID uuid) throws SQLException {
        User user = new User(uuid);
        ResultSet rs = this.mySQLSkyWars.execute("SELECT * FROM " + Settings.getInstance().getConfig().mysqlSwUserTable + " WHERE `uuid`='" + uuid.toString() + "';");
        if (rs.next()) {
            try {
                user.setName(rs.getString("name"));
                user.setKills(rs.getInt("kills"));
                user.setDeaths(rs.getInt("deaths"));
                user.setGamesPlayed(rs.getInt("gamesPlayed"));
                user.setGamesWon(rs.getInt("gamesWon"));
                user.setPoints(rs.getInt("points"));
            } catch (Exception ex) {
                LogUtils.exception(ex);
            }
        }
        rs = this.mySQLCoins.execute("SELECT * FROM " + Settings.getInstance().getConfig().mysqlCoinsTable + " WHERE `uuid`='" + uuid.toString() + "';");
        if (rs.next()) {
            try {
                user.setCoins(rs.getInt("coins"));
                user.setTokens(rs.getInt("tokens"));
            } catch (Exception ex) {
                LogUtils.exception(ex);
            }
        }
        return user;
    }

    public void saveUser(User user) {
        String query = "INSERT INTO " + Settings.getInstance().getConfig().mysqlSwUserTable + " (`uuid`, `name`, `kills`, `deaths`, `gamesPlayed`, `gamesWon`, `points`) VALUES (" +
                "'" + user.getUuid() + "'," +
                "'" + user.getName() + "'," +
                "'" + user.getKills() + "'," +
                "'" + user.getDeaths() + "'," +
                "'" + user.getGamesPlayed() + "'," +
                "'" + user.getGamesWon() + "'," +
                "'" + user.getPoints() + "'" +
                ") ON DUPLICATE KEY UPDATE " +
                "`name`='" + user.getName() + "'," +
                "`kills`='" + user.getKills() + "'," +
                "`deaths`='" + user.getDeaths() + "'," +
                "`gamesPlayed`='" + user.getGamesPlayed() + "'," +
                "`gamesWon`='" + user.getGamesWon() + "'," +
                "`points`='" + user.getPoints() + "';";
        this.mySQLSkyWars.update(query);

        query = "INSERT INTO " + Settings.getInstance().getConfig().mysqlCoinsTable + " (`uuid`, `name`, `coins`, `tokens`) VALUES (" +
                "'" + user.getUuid() + "'," +
                "'" + user.getName() + "'," +
                "'" + user.getCoins() + "'," +
                "'" + user.getTokens() + "'" +
                ") ON DUPLICATE KEY UPDATE " +
                "`name`='" + user.getName() + "'," +
                "`coins`='" + user.getCoins() + "'," +
                "`tokens`='" + user.getTokens() + "';";
        this.mySQLCoins.update(query);
    }
}
