package cf.criliscraft.playerdata.lib;

import cf.criliscraft.playerdata.PlayerData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class SQLActions {

    private final PlayerData plugin;

    private long cur = System.currentTimeMillis();

    private String host = ConfigLib.getConfig().getString("mysql.host");
    private String db = ConfigLib.getConfig().getString("mysql.database");
    private String user = ConfigLib.getConfig().getString("mysql.username");
    private String pass = ConfigLib.getConfig().getString("mysql.password");

    public SQLActions(PlayerData plugin) {
        this.plugin = plugin;
    }

    private Connection getConnection() {

        String url = "jdbc:mysql://" + host + "/" + db;
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            this.plugin.getLogger().log(Level.WARNING, "ERROR: " + e);
        } catch (SQLException e) {
            this.plugin.getLogger().log(Level.WARNING, "ERROR: " + e);
        }

        return con;
    }

    public void createPlayerRowLogin(String username, String uuid) {

        String query = "INSERT INTO players (username, uuid, joinedOn, joinTime, lastLogin, logins) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, uuid);
            stmt.setLong(3, cur);
            stmt.setLong(4, cur);
            stmt.setLong(5, cur);
            stmt.setInt(6, 1);

            stmt.execute();

            getConnection().close();
        } catch (SQLException e) {
            this.plugin.getLogger().log(Level.WARNING, "ERROR: " + e);
        }
    }

    public void updatePlayerRowLogin(String username) {

        String query = "UPDATE players SET joinTime=?, lastLogin=?, logins=? WHERE username=?";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setLong(1, cur);
            stmt.setLong(2, cur);
            stmt.setInt(3, 2);
            stmt.setString(4, username);
        } catch (SQLException e) {
            this.plugin.getLogger().log(Level.WARNING, "ERROR: " + e);
        }
    }

    public boolean hasPlayedBefore(String username) {

        if (username.equals("Chaka")) {
            return true;
        } else {
            return false;
        }
    }
}
