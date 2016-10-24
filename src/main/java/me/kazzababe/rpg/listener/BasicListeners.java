package me.kazzababe.rpg.listener;

import me.kazzababe.rpg.Forged;
import me.kazzababe.rpg.player.ForgedPlayer;
import me.kazzababe.rpg.quest.Quest001PigKiller;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BasicListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ForgedPlayer player = Forged.getPlayer(event.getPlayer().getUniqueId());
        player.startQuest(new Quest001PigKiller(player));
    }
}
