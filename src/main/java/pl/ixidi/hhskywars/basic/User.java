package pl.ixidi.hhskywars.basic;

import java.util.UUID;

public class User {

    private String name;
    private UUID uuid;

    private int points;

    private int tokens;
    private int coins;

    private int kills;
    private int deaths;
    private int gamesPlayed;
    private int gamesWon;

    private Game game;

    public User(UUID uuid) {
        this.uuid = uuid;

        this.points = 0;
        this.tokens = 0;
        this.coins = 0;
        this.kills = 0;
        this.deaths = 0;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
    }

    public void setName(String name) {
        this.name = name;
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
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
