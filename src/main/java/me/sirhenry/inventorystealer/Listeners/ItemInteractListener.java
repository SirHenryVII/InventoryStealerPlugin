package me.sirhenry.inventorystealer.Listeners;

import me.sirhenry.inventorystealer.DataStorer;
import me.sirhenry.inventorystealer.items.InventoryBarrier;
import me.sirhenry.inventorystealer.items.ItemSlot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemInteractListener implements Listener {

    @EventHandler
    public void onItemInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();

        if(e.getItem() == null){
            return;
        }

        //cancel any event to do with inventory barriers that isn't left clicking
        if(e.getItem().isSimilar(InventoryBarrier.getItem()) && (e.getAction() != Action.LEFT_CLICK_BLOCK || e.getAction() != Action.LEFT_CLICK_AIR)) e.setCancelled(true);

        //Check if event is right click
        if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }

        //check if Item is ItemSlot item
        if(player.getInventory().getItemInMainHand().isSimilar(ItemSlot.getItem())){

            //Check if they can have any more slots
            if(DataStorer.getBarrierNum(player) <= 0){
                player.sendMessage("§cYou Already have the Maximum Allowed Slots.");
                return;
            }

            //remove item from inventory
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);

            //add slot
            DataStorer.setBarrierNum(player, DataStorer.getBarrierNum(player) - 1);
            DataStorer.updateInventory(player);

            //send message
            player.sendMessage("§aYou Just Gave Yourself an Inventory Slot!");
        }
    }
}
