package me.kazzababe.rpg;

import me.kazzababe.rpg.listener.BasicListeners;
import me.kazzababe.rpg.listener.ForgedListeners;
import me.kazzababe.rpg.player.ForgedPlayer;
import me.kazzababe.rpg.quest.QuestManager;
import me.kazzababe.rpg.sql.SQL;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.jar.Pack200;

public class Forged extends JavaPlugin {
    private static Forged plugin;
    private static HashMap<UUID, ForgedPlayer> players = new HashMap<UUID, ForgedPlayer>();

    private QuestManager questManager;

    private static SQL sql;

    @Override
    public void onEnable() {
        plugin = this;

        this.questManager = new QuestManager();

        this.getServer().getPluginManager().registerEvents(new ForgedListeners(), this);
        this.getServer().getPluginManager().registerEvents(new BasicListeners(), this);
    }

    @Override
    public void onLoad() {
        sql = new SQL(this);
    }

    @Override
    public void onDisable() {

    }

    public static Forged getPlugin() {
        return plugin;
    }

    public SQL getSQL() {
        return sql;
    }

    public static ForgedPlayer getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public static void addPlayer(UUID uuid, String name) {
        players.put(uuid, new ForgedPlayer(0, uuid, name));
    }

    public static void removePlayer(ForgedPlayer player) {
        players.remove(player.getUniqueId());
    }
}
