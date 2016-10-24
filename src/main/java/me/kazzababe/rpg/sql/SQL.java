package me.kazzababe.rpg.sql;

import me.kazzababe.rpg.Forged;
import me.kazzababe.rpg.quest.Quest;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class SQL {
    private final Forged plugin;
    private Connection connection;

    public SQL(Forged plugin) {
        this.plugin = plugin;
    }

    public synchronized Connection getConnection() throws SQLException {
        if (this.connection != null) {
            if (this.connection.isValid(1)) {
                return this.connection;
            }
            this.connection.close();
        }
        FileConfiguration config = this.plugin.getConfig();
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + config.getString("db_hostname") + ":" + config.getInt("db_port") + "/rpg",
                    config.getString("db_user"),
                    config.getString("db_pass"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.connection;
    }

    public void createQuestTable(Quest quest) {
        String sql = "CREATE TABLE IF NOT EXISTS `quest_" + quest.getId() + "' (`id` MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT, `player` MEDIUMINT(8) UNSIGNED NOT NULL, `completed` BOOLEAN DEFAULT 0, `stage` MEDIUMINT(8) UNSIGNED NOT NULL, ";

        String[] entries = new String[quest.getData().size()];
        int count = 0;
        for (HashMap.Entry<String, Object> data : quest.getData().entrySet()) {
            String key = data.getKey();
            Object value = data.getValue();

            String entry = "`" + key + "`";
            if (value instanceof Long) {
                entry += " BIGINT NOT NULL";
            } else if (value instanceof Integer) {
                entry += " INTEGER NOT NULL";
            } else if (value instanceof Double) {
                entry += " FLOAT NOT NULL";
            } else if (value instanceof Boolean) {
                entry += " BOOLEAN DEFAULT 0";
            } else {
                entry += " VARCHAR(max) NOT NULL";
            }
            entries[count] = entry;
            count++;
        }
        String insert = StringUtils.join(entries, ", ");
        sql += insert + ", PRIMARY KEY (`id`), UNIQUE (`player`))";
System.out.println(sql);
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
