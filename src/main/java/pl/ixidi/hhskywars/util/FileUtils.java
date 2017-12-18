package pl.ixidi.hhskywars.util;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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

    public static void deleteAll(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files == null) {
                directory.delete();
                return;
            }
            Arrays.asList(files).forEach(FileUtils::deleteAll);
        } else {
            directory.delete();
        }
    }

    private FileUtils() {}

}
