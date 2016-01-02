package cf.criliscraft.playerdata.lib;

import cf.criliscraft.playerdata.PlayerData;
import org.bukkit.ChatColor;

public class ChatLib {

    public static class Messages {

        public static final String CHAT_PREFIX = ChatColor.WHITE + "[" + ChatColor.GREEN + "Player" + ChatColor.BLUE + "Data" + ChatColor.WHITE + "] " + ChatColor.DARK_GREEN;

        public static final String NO_PERMS = CHAT_PREFIX + ChatColor.RED + "You do not have permission to use that command.";

        public static final String PL_VERSION = CHAT_PREFIX + "Version: " + ChatColor.DARK_AQUA + PlayerData.VERSION;

        public static final String CMD_PD_USEHELP = CHAT_PREFIX + "Use " + ChatColor.DARK_AQUA + "/pd help" + ChatColor.DARK_GREEN + " for a list of valid sub-commands.";
    }

    public static class Returns {

    }
}
