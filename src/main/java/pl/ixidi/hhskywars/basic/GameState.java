package pl.ixidi.hhskywars.basic;

public enum GameState {

    DISABLED("&4Wylaczona"),
    PREPARING("&ePrzygotowywana"),
    WAITING("&6Czeka na graczy"),
    FULL("&6Pelna"),
    CONTINUES("&2Trwa");

    private String status;

    GameState(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
