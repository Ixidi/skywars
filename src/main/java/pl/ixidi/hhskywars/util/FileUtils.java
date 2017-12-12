package pl.ixidi.hhskywars.util;


import java.io.File;
import java.io.IOException;

public final class FileUtils {

    public static void createIfNotExists(File... files) {
        for (File file : files) {
            if (!file.exists()) {
                try {
                    if (file.isDirectory()) {
                        file.mkdirs();
                    } else {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                } catch (IOException ex) {
                    LogUtils.exception(ex);
                }
            }
        }
    }

    private FileUtils() {}

}
