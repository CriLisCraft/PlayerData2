package cf.criliscraft.playerdata.lib;

import cf.criliscraft.playerdata.PlayerData;

import java.sql.*;
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

    public boolean playersTableExists() {
        try {
            DatabaseMetaData dbm = getConnection().getMetaData();

            ResultSet res = dbm.getTables(null, null, "players", null);

            if (res.next()) {
                return true; //Exists
            } else {
                return false; //Does not exist
            }
        } catch (SQLException e) {
            this.plugin.getLogger().log(Level.WARNING, "ERROR: " + e);
        }

        return false;
    }

    public void installDatabase() {
        if (!(playersTableExists())) {
            try {
                Statement stmt = getConnection().createStatement();
                String sql = "CREATE TABLE " + db + ".players (\n" +
                        "\tid INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,\n" +
                        "    username VARCHAR(100),\n" +
                        "    uuid VARCHAR(500),\n" +
                        "    joinedOn BIGINT(20),\n" +
                        "    joinTime BIGINT(20) DEFAULT '0',\n" +
                        "    lastLogin BIGINT(20) DEFAULT '0',\n" +
                        "    logins INT(11) DEFAULT '0',\n" +
                        "    logouts INT(11) DEFAULT '0',\n" +
                        "    playtime BIGINT(20) DEFAULT '0'\n" +
                        ")";

                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                this.plugin.getLogger().log(Level.WARNING, "ERROR: " + e);
            }
        }
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

    public void updatePlayerRowLogin(String uuid) {

        String query = "UPDATE players SET joinTime=?, lastLogin=?, logins=? WHERE uuid=?";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setLong(1, cur);
            stmt.setLong(2, cur);
            stmt.setInt(3, 2);
            stmt.setString(4, uuid);
        } catch (SQLException e) {
            this.plugin.getLogger().log(Level.WARNING, "ERROR: " + e);
        }
    }

    public boolean hasPlayedBefore(String uuid) {

        String query = "SELECT * FROM players WHERE uuid=?";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, uuid);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            this.plugin.getLogger().log(Level.WARNING, "ERROR: " + e);
        }

        return false;
    }
}
