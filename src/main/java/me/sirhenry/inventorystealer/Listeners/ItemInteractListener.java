package me.sirhenry.inventorystealer.Listeners;

import me.sirhenry.inventorystealer.DataStorer;
import me.sirhenry.inventorystealer.items.ItemSlot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.w3c.dom.stylesheets.LinkStyle;

public class ItemInteractListener implements Listener {

    @EventHandler
    public void onItemInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();

        //Check if event is right click
        if(e.getAction() != Action.RIGHT_CLICK_AIR || e.getAction() != Action.RIGHT_CLICK_BLOCK){
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
        }
    }
}
