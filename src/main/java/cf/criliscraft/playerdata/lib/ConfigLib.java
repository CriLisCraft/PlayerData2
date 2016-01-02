package cf.criliscraft.playerdata.lib;

import cf.criliscraft.playerdata.PlayerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigLib {

    private static PlayerData plugin;

    private static File dataFolder;

    private static File configFile;
    private static File sqlInfo;

    private static FileConfiguration config;

    @SuppressWarnings("static-access")
    public ConfigLib(PlayerData plugin) {
        this.plugin = plugin;

        this.dataFolder = plugin.getDataFolder();

        this.configFile = new File(plugin.getDataFolder(), "config.yml");
        this.sqlInfo = new File(plugin.getDataFolder(), "INSTALL.txt");

        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static void createAllFiles() {
        if (!(configFile.exists())) {
            plugin.getLogger().log(Level.INFO, "config.yml not found, Creating new one.");
            plugin.saveResource("config.yml", true);
            plugin.getLogger().log(Level.INFO, "Successfully created config.yml");
        }
        if (!(sqlInfo.exists())) {
            plugin.getLogger().log(Level.INFO, "INSTALL.txt not found, Creating new one.");
            plugin.saveResource("INSTALL.txt", true);
            plugin.getLogger().log(Level.INFO, "Successfully created install.TXT");
        }
    }

    public static File getConfigFile() {
        return configFile;
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getFileConfig(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void saveConfigFile() {
        saveFile(configFile, config);
    }

    public static void saveFile(File file, FileConfiguration config) {
        try {
            config.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
