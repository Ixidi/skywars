package pl.ixidi.hhskywars.util;

import pl.ixidi.hhskywars.SkyWarsPlugin;

import java.util.logging.Logger;

public final class LogUtils {

    private static final Logger LOGGER = SkyWarsPlugin.getPlugin(SkyWarsPlugin.class).getLogger();

    public static void info(String text) {
        LOGGER.info(text);
    }

    public static void warning(String text) {
        LOGGER.warning(text);
    }

    public static void error(String text) {
        LOGGER.severe(text);
    }

    public static void exception(Exception exception) {
        error("Error!");
        error("Caused by:" + exception.getCause().toString());
        error("Stack trace: ");
        for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
            error(stackTraceElement.toString());
        }
        error("End.");
        error("");
    }

    private LogUtils() { }

}
