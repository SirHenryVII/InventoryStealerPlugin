package me.sirhenry.inventorystealer.Listeners;

import me.sirhenry.inventorystealer.DataStorer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        DataStorer.updateInventory(e.getPlayer());
    }
}
