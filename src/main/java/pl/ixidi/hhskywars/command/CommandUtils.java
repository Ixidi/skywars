package pl.ixidi.hhskywars.command;

import pl.ixidi.hhskywars.data.Settings;
import pl.ixidi.hhskywars.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class CommandUtils {

    public static String getUsage(CommandHandler handler, String... args) {
        CommandHandler checkerHandler = handler;
        ArrayList<String> subcommands = new ArrayList<>();
        while (checkerHandler != null) {
            subcommands.add(checkerHandler.getName());
            checkerHandler = checkerHandler.getParent();
        }
        StringBuilder builder = new StringBuilder();
        for (int i = subcommands.size() - 1; i >= 0; i--) {
            builder.append(subcommands.get(i)).append(" ");
        }
        for (int i = 0; i < args.length; i++) {
            builder.append("<").append(args[i]).append(">");
            if (i != args.length - 1) {
                builder.append(" ");
            }
        }
        String message = Settings.getInstance().getMessages().commandUsage;
        message = StringUtils.replace(message, "{USAGE}", builder.toString());
        return message;
    }

    private CommandUtils() { }

}
