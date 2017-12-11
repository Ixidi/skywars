package pl.ixidi.hhskywars.data;

import pl.ixidi.hhskywars.data.config.PluginConfig;
import pl.ixidi.hhskywars.data.config.PluginMessages;
import pl.ixidi.hhskywars.util.LogUtils;
import pl.ixidi.hhskywars.util.StringUtils;

import java.lang.reflect.Field;

public class Settings {

    private static Settings instance;

    private PluginMessages messages;
    private PluginConfig config;

    public Settings() {
        instance = this;
        this.messages = new PluginMessages();
        this.config = new PluginConfig();
        this.config.load();
        try {
            colorMessages();
        } catch (IllegalAccessException ex) {
            LogUtils.exception(ex);
        }
    }

    private void colorMessages() throws IllegalAccessException {
        Class c = messages.getClass();
        for (Field field : c.getFields()) {
            Object value = field.get(this.messages);
            if (field.getType() == String.class) {
                field.set(this.messages, StringUtils.color((String) value));
            }
        }
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public PluginMessages getMessages() {
        return messages;
    }

    public PluginConfig getConfig() {
        return config;
    }
}
