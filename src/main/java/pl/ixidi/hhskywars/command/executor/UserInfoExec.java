package pl.ixidi.hhskywars.command.executor;

import org.bukkit.command.CommandSender;
import pl.ixidi.hhskywars.basic.User;
import pl.ixidi.hhskywars.basic.util.UserUtils;
import pl.ixidi.hhskywars.command.CommandHandler;
import pl.ixidi.hhskywars.command.CommandUtils;
import pl.ixidi.hhskywars.command.Executor;
import pl.ixidi.hhskywars.util.StringUtils;

public class UserInfoExec implements Executor {

    @Override
    public void execute(CommandSender sender, CommandHandler handler, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(CommandUtils.getUsage(handler, "nick"));
            return;
        }
        String nick = args[0];
        User user = UserUtils.get(nick);
        if (user == null) {
            sender.sendMessage(StringUtils.color("&cGracz o nicku &7" + nick + " &cnie istnieje!"));
            return;
        }
        sender.sendMessage(user.getName() + "" + user.getPoints() + " " + user.getDeaths());
    }

}
