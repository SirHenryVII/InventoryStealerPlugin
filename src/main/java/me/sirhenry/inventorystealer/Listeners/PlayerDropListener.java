package me.sirhenry.inventorystealer.Listeners;

import me.sirhenry.inventorystealer.items.InventoryBarrier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropListener implements Listener {

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e){
        //make sure player can't drop inventory barrier
        if(e.getItemDrop().getItemStack().isSimilar(InventoryBarrier.getItem())) e.setCancelled(true);
    }
}
