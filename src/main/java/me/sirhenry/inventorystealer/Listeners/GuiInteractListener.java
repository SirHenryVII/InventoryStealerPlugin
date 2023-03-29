package me.sirhenry.inventorystealer.Listeners;

import me.sirhenry.inventorystealer.items.InventoryBarrier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;

public class GuiInteractListener implements Listener {
    @EventHandler
    public void onGuiInteract(InventoryClickEvent e){

        //Check if there is an item
        if(e.getCurrentItem() == null) return;

        //check if that item is a barrier
        if(!e.getCurrentItem().isSimilar(InventoryBarrier.getItem())) return;

        //send message and cancel event if both conditions are true
        e.getView().getPlayer().sendMessage("§cThat is a Locked Inventory Slot!");
        e.setCancelled(true);
    }
}
