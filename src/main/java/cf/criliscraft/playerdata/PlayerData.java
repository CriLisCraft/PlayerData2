package cf.criliscraft.playerdata;

import cf.criliscraft.playerdata.command.PDCommand;
import cf.criliscraft.playerdata.lib.ConfigLib;
import cf.criliscraft.playerdata.lib.PermLib;
import cf.criliscraft.playerdata.lib.SQLActions;
import cf.criliscraft.playerdata.listener.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class PlayerData extends JavaPlugin {

    public static final String VERSION = "2.0.0-B6";

    private SQLActions sql;

    private PluginManager pm = this.getServer().getPluginManager();

    @Override
    public void onDisable() {

        //Save config file
        ConfigLib.saveConfigFile();

        this.getLogger().log(Level.INFO, "Disabled");
    }

    @Override
    public void onEnable() {

        //Config initialization
        new ConfigLib(this);
        ConfigLib.createAllFiles();

        //Perm Lib Initialization
        PermLib.init(this.pm);

        //Initialize SQLActions
        new SQLActions(this);
        //TODO Null pointer
        //sql.installDatabase();

        //Listeners
        new PlayerListener(this);

        //Command Registration
        this.getCommand("pd").setExecutor(new PDCommand(this));

        this.getLogger().log(Level.INFO, "Enabled");
    }
}
