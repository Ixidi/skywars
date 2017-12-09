package pl.ixidi.hhskywars.data.config;

public class PluginConfig {

    public int dataContainer = 0;

    public void load() {
        if (dataContainer < 0 || dataContainer > 1) {
            dataContainer = 0;
        }
    }
}
