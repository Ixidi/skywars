package pl.ixidi.hhskywars.util;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YamlFile extends YamlConfiguration {

    private File file;

    public YamlFile(File file) {
        super();
        this.file = file;
        FileUtils.createIfNotExists(file);
        try {
            super.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            LogUtils.exception(ex);
        }
    }

    public void save() {
        try {
            super.save(this.file);
        } catch (IOException ex) {
            LogUtils.exception(ex);
        }
    }

}
