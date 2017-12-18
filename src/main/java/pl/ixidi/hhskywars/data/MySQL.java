package pl.ixidi.hhskywars.data;

import com.zaxxer.hikari.HikariDataSource;
import pl.ixidi.hhskywars.data.config.PluginConfig;
import pl.ixidi.hhskywars.util.LogUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    private HikariDataSource hikari;

    public MySQL(String host, int port, String user, String password, String database, int pool) {
        this.hikari = new HikariDataSource();
        this.hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        this.hikari.addDataSourceProperty("serverName", host);
        this.hikari.addDataSourceProperty("port", port);
        this.hikari.addDataSourceProperty("databaseName", database);
        this.hikari.addDataSourceProperty("user", user);
        this.hikari.addDataSourceProperty("password", password);
        this.hikari.addDataSourceProperty("cachePrepStmts", "true");
        this.hikari.addDataSourceProperty("prepStmtCacheSize", "250");
        this.hikari.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.hikari.setMaximumPoolSize(pool);
    }

    public void update(String query) {
        try {
            PreparedStatement statement = this.hikari.getConnection().prepareStatement(query);
            statement.executeUpdate();
            statement.close();
        } catch (Exception ex) {
            LogUtils.exception(ex);
        }
    }

    public ResultSet execute(String query) throws SQLException {
        PreparedStatement statement = this.hikari.getConnection().prepareStatement(query);
        return statement.executeQuery();
    }
}
