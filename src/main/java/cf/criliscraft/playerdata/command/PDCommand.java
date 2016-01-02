package cf.criliscraft.playerdata.command;

import cf.criliscraft.playerdata.PlayerData;
import cf.criliscraft.playerdata.lib.ChatLib;
import cf.criliscraft.playerdata.lib.PermLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PDCommand implements CommandExecutor {

    private final PlayerData plugin;

    public PDCommand(PlayerData plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender source, Command cmd, String alias, String[] args) {

        int length = args.length;

        if (cmd.getName().equalsIgnoreCase("pd")) {
            if (source.hasPermission(PermLib.CMD_PD)) {
                source.sendMessage(ChatLib.Messages.CMD_PD_USEHELP);
                source.sendMessage(ChatLib.Messages.PL_VERSION);

                return true;
            } else {
                source.sendMessage(ChatLib.Messages.NO_PERMS);
                return true;
            }
        }

        return false;
    }
}
