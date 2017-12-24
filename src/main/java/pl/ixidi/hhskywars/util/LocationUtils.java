package pl.ixidi.hhskywars.util;

import org.bukkit.Location;
import org.bukkit.World;
import pl.ixidi.hhskywars.SkyWarsPlugin;

public final class LocationUtils {

    public static Location toLcation(String text) {
        String[] splited = text.split(";");
        try {
            Double x = Double.parseDouble(splited[0]);
            Double y = Double.parseDouble(splited[1]);
            Double z = Double.parseDouble(splited[2]);
            String worldString = splited[3];
            World world = SkyWarsPlugin.getPlugin(SkyWarsPlugin.class).getServer().getWorld(worldString);
            if (world == null) {
                return null;
            }
            return new Location(world, x, y, z);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Location toLcation(String text, World world) {
        String[] splited = text.split(";");
        try {
            Double x = Double.parseDouble(splited[0]);
            Double y = Double.parseDouble(splited[1]);
            Double z = Double.parseDouble(splited[2]);
            return new Location(world, x, y, z);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String toString(Location location) {
        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        return x + ";" + y + ";" + z + ";" + world;
    }

    public static String toLocationString(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        return "X: " + x + " Y: " + y + " Z: " + z;
    }

    private LocationUtils() { }

}
