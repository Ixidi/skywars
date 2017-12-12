package pl.ixidi.hhskywars.basic.util;

import pl.ixidi.hhskywars.basic.Game;

import java.util.HashMap;

public final class GameUtils {

    private static HashMap<Integer, Game> gameMap = new HashMap<>();

    public static void add(Game game) {
        gameMap.put(game.getId(), game);
    }

    public static void remove(Game game) {
        if (gameMap.containsValue(game)) {
            gameMap.remove(game.getId());
        }
    }

    public static Game get(int id) {
        return gameMap.get(id);
    }

    public static HashMap<Integer, Game> getGameMap() {
        return gameMap;
    }
    
    private GameUtils() { }

}
