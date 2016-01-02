package cf.criliscraft.playerdata.listener;

import cf.criliscraft.playerdata.PlayerData;
import cf.criliscraft.playerdata.lib.SQLActions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final PlayerData plugin;
    private SQLActions sql;


    public PlayerListener(PlayerData plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        String username = player.getName();
        String uuid = player.getUniqueId().toString();

        if (sql.hasPlayedBefore(username)) {
            sql.updatePlayerRowLogin(username);
        } else {
            sql.createPlayerRowLogin(username, uuid);
        }
    }
}
