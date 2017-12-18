package pl.ixidi.hhskywars.data.config;

public class PluginConfig {

    public int dataContainer = 0;
    public int gamesCount = 1;

    public String mysqlSwHost = "localhost";
    public int mysqlSwPort = 3306;
    public int mysqlSwPool = 10;
    public String mysqlSwUser = "root";
    public String mysqlSwPassword = "password1234";
    public String mysqlSwDatabase = "skywars";
    public String mysqlSwUserTable = "users";

    public String mysqlCoinsHost = "localhost";
    public int mysqlCoinsPort = 3306;
    public int mysqlCoinsPool = 10;
    public String mysqlCoinsUser = "root";
    public String mysqlCoinsPassword = "password1234";
    public String mysqlCoinsDatabase = "skywars";
    public String mysqlCoinsTable = "coins";


    public void load() {
        if (this.dataContainer > 1 || this.dataContainer < 0) {
            this.dataContainer = 0;
        }
        if (gamesCount < 0) {
            this.gamesCount = 1;
        }
    }
}
