package me.kazzababe.rpg.player;

import lombok.Getter;
import me.kazzababe.rpg.quest.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ForgedPlayer {
    /**
     * The unique id that identifies a player in the database
     */
    @Getter
    private int id;

    @Getter
    private UUID uniqueId;
    @Getter
    private String name;

    /**
     * A list of quests that the player has started
     */
    private Set<Quest> activeQuests = new HashSet<Quest>();
    /**
     * A list of all the id's of quests that the player has finished
     */
    private Set<Integer> completedQuests = new HashSet<Integer>();

    public long experience;

    @Getter
    private int basePhysicalResistance;
    @Getter
    private int baseElementalResistance;
    @Getter
    private int baseFireResistance;
    @Getter
    private int baseWaterResistance;
    @Getter
    private int baseAirResistance;
    @Getter
    private int baseEarthResistance;

    @Getter
    private int baseMaxHealth;
    @Getter
    private int health;
    @Getter
    private double baseHealthRegen;

    @Getter
    private int baseCriticalHitChance;
    @Getter
    private int baseCriticalHitDamage;

    @Getter
    private int baseMovementSpeedBonus;
    @Getter
    private int baseCooldownReduction;

    public ForgedPlayer(int id, UUID uuid, String name) {
        this.id = id;
        this.uniqueId = uuid;
        this.name = name;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uniqueId);
    }

    public void startQuest(Quest quest) {
        this.activeQuests.add(quest);
    }

    public void completeQuest(Quest quest) {
        this.activeQuests.remove(quest);
        this.completedQuests.add(quest.getId());
    }

    public void die() {
        this.getPlayer().setHealth(0);
    }

    public void updateHealth(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.die();
        }
        this.getPlayer().setHealth(Math.max(1.0, (double) this.health / this.getMaxHealth()));
    }

    public int getPhysicalResistance() {
        return this.basePhysicalResistance;
    }

    public int getFireResistance() {
        return this.baseElementalResistance + this.baseFireResistance;
    }

    public int getWaterResistance() {
        return this.baseElementalResistance + this.baseWaterResistance;
    }

    public int getAirResistance() {
        return this.baseElementalResistance + this.baseAirResistance;
    }

    public int getEarthResistance() {
        return this.baseElementalResistance + this.baseEarthResistance;
    }

    public int getMaxHealth() {
        return this.baseMaxHealth;
    }

    public double getHealthRegen() {
        return this.baseHealthRegen;
    }

    public int getCriticalHitChance() {
        return this.baseCriticalHitChance;
    }

    public int getCriticalHitDamage() {
        return this.baseCriticalHitDamage;
    }

    public int getMovementSpeedBonus() {
        return this.baseMovementSpeedBonus;
    }

    public int getCooldownReduction() {
        return this.baseCooldownReduction;
    }
}
