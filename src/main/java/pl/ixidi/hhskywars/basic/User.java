package pl.ixidi.hhskywars.basic;

import java.util.UUID;

public class User {

    private String name;
    private UUID uuid;

    private int points;
    private int tokens;
    private int money;

    private int kills;
    private int deaths;
    private int gamesPlayed;
    private int gamesWon;

    private Game game;

    private boolean loaded;
    private boolean changed;

    public User(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
        this.loaded = false;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        if (this.loaded && !this.changed) {
            this.changed = true;
        }
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
        if (this.loaded && !this.changed) {
            this.changed = true;
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
        if (this.loaded && !this.changed) {
            this.changed = true;
        }
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
        if (this.loaded && !this.changed) {
            this.changed = true;
        }
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
        if (this.loaded && !this.changed) {
            this.changed = true;
        }
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
        if (this.loaded && !this.changed) {
            this.changed = true;
        }
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
        if (this.loaded && !this.changed) {
            this.changed = true;
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        if (this.loaded && !this.changed) {
            this.changed = true;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void loaded() {
        this.loaded = true;
    }
}
