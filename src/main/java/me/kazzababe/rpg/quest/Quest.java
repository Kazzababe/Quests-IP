package me.kazzababe.rpg.quest;

import lombok.Getter;
import lombok.Setter;
import me.kazzababe.rpg.Forged;
import me.kazzababe.rpg.player.ForgedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Quest implements Listener {
    @Getter
    private ForgedPlayer owningPlayer;

    /**
     * A list of strings that is used to display an objective to the player, corresponds to stage
     */
    private HashMap<Integer, String> objectives = new HashMap<Integer, String>();

    /**
     * Custom data to be stored in the database. Used to track specific details for the player/quest
     */
    @Getter
    private HashMap<String, Object> data = new HashMap<String, Object>();

    @Getter
    @Setter
    private int stage;

    public Quest(ForgedPlayer player) {
        this.owningPlayer = player;

        Bukkit.getPluginManager().registerEvents(this, Forged.getPlugin());
    }

    public abstract int getId();
    public abstract String getName();

    protected void addObjective(int index, String objective) {
        this.objectives.put(index, objective);
    }

    public void setData(String key, Object value) {
        this.data.put(key, value);
    }

    public Object getData(String key) {
        return this.data.get(key);
    }
}
