package pl.ixidi.hhskywars.data;

import pl.ixidi.hhskywars.SkyWarsPlugin;
import pl.ixidi.hhskywars.data.config.PluginConfig;
import pl.ixidi.hhskywars.data.data.UserData;

public class DataManager {

    private static DataManager instance;

    private MySQL mySQLSkyWars;
    private MySQL mySQLCoins;
    private UserData userData;

    public DataManager() {
        instance = this;
        PluginConfig c = Settings.getInstance().getConfig();
        this.mySQLSkyWars = new MySQL(c.mysqlSwHost, c.mysqlSwPort, c.mysqlSwUser, c.mysqlSwPassword, c.mysqlSwDatabase, c.mysqlSwPool);
        if (c.mysqlSwHost.equals(c.mysqlCoinsHost) && c.mysqlSwUser.equals(c.mysqlCoinsUser) && c.mysqlSwPassword.equals(c.mysqlCoinsPassword) && c.mysqlSwDatabase.equals(c.mysqlCoinsDatabase)) {
            this.mySQLCoins = mySQLSkyWars;
        } else {
            this.mySQLCoins = new MySQL(c.mysqlCoinsHost, c.mysqlCoinsPort, c.mysqlCoinsUser, c.mysqlCoinsPassword, c.mysqlCoinsDatabase, c.mysqlCoinsPool);
        }
        this.userData = new UserData(this.mySQLSkyWars, this.mySQLCoins);
        this.usersTable();
        this.coinsTable();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            return new DataManager();
        }
        return instance;
    }


    public UserData getUserData() {
        return userData;
    }

    private void usersTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + Settings.getInstance().getConfig().mysqlSwUserTable + " (" +
                "uuid VARCHAR(50) NOT NULL," +
                "name VARCHAR(50) NOT NULL," +
                "kills INT NOT NULL," +
                "deaths INT NOT NULL," +
                "gamesPlayed INT NOT NULL," +
                "gamesWon INT NOT NULL," +
                "points INT NOT NULL," +
                "PRIMARY KEY (uuid));";
        this.mySQLSkyWars.update(query);
    }

    private void coinsTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + Settings.getInstance().getConfig().mysqlCoinsTable + " (" +
                "uuid VARCHAR(50) NOT NULL," +
                "name VARCHAR(50) NOT NULL," +
                "coins INT NOT NULL," +
                "tokens INT NOT NULL," +
                "PRIMARY KEY (uuid));";
        this.mySQLCoins.update(query);
    }
}
