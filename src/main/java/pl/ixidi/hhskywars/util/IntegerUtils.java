package pl.ixidi.hhskywars.util;

public final class IntegerUtils {

    public static Integer toPositiveInteger(String text) {
        Integer toReturn;
        try {
            toReturn = Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return null;
        }
        if (toReturn <= 0) {
            return null;
        }
        return toReturn;
    }

    private IntegerUtils() { }

}
