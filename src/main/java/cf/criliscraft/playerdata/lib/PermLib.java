package cf.criliscraft.playerdata.lib;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

public class PermLib {

    public static void init(PluginManager pm) {
        pm.addPermission(CMD_PD);
    }

    public static Permission CMD_PD = new Permission("pd.cmd");
}