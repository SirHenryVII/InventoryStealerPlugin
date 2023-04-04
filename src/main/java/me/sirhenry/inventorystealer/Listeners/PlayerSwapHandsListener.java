package me.sirhenry.inventorystealer.Listeners;

import me.sirhenry.inventorystealer.items.InventoryBarrier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandsListener implements Listener {

    @EventHandler
    public void onPlayerSwapHands(PlayerSwapHandItemsEvent e){
        //cancel event if player tries to swap inventory barrier
        if(e.getOffHandItem().isSimilar(InventoryBarrier.getItem())) e.setCancelled(true);
    }
}
