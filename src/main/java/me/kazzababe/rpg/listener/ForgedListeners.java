package me.kazzababe.rpg.listener;

import me.kazzababe.rpg.Forged;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ForgedListeners implements Listener {
    @EventHandler
    public void onPlayerLogin(AsyncPlayerPreLoginEvent event) {
        Forged.addPlayer(event.getUniqueId(), event.getName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Forged.removePlayer(Forged.getPlayer(event.getPlayer().getUniqueId()));
    }
}
