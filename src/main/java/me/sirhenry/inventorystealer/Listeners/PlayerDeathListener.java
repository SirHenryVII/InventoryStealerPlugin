package me.sirhenry.inventorystealer.Listeners;

import me.sirhenry.inventorystealer.Config;
import me.sirhenry.inventorystealer.DataStorer;
import me.sirhenry.inventorystealer.items.InventoryBarrier;
import me.sirhenry.inventorystealer.items.ItemSlot;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class PlayerDeathListener implements Listener {

    @EventHandler

    public void onPlayerDeath(PlayerDeathEvent e){

        Player player = e.getEntity();

        //make sure player can't drop barriers
        while(e.getDrops().remove(InventoryBarrier.getItem())){
            e.getDrops().remove(InventoryBarrier.getItem());
        }


        //if died of natural causes
        if(player.getKiller() == null){
            if(Config.getLoseSlotWhenNotKilledByPlayer()){
                DataStorer.setBarrierNum(player, DataStorer.getBarrierNum(player) + 1);
            }
            return;
        }


        Player killer = player.getKiller();
        int playerBarrierNum = DataStorer.getBarrierNum(player);
        int killerBarrierNum = DataStorer.getBarrierNum(killer);

        //if Killer has max inventory slots, drop inventory slot
        if(killerBarrierNum - 1 < 0){
            player.getWorld().dropItem(player.getLocation(), ItemSlot.getItem());
            killer.sendMessage("§cYou Already have the Maximum Amount of Inventory Slots, so One was Dropped on the Ground.");
        }else{
            DataStorer.setBarrierNum(killer, killerBarrierNum - 1);
            killer.sendMessage("§aYou just Gained 1 Inventory Slot from " + player.getName() + "!");
        }

        if(playerBarrierNum + 1 >= 36){
            //Death Condition
            DataStorer.addDead(player);
            if(Config.getBanOnDeath()){
                Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), "§cYou Lost All of Your Inventory Slots.", null, null);
                player.kickPlayer("§cYou Lost All of Your Inventory Slots.");
            }
            else{
                player.setGameMode(GameMode.SPECTATOR);
            }
        }
        else{
            DataStorer.setBarrierNum(player, playerBarrierNum + 1);
        }


        //victim will respawn so no need to update inventory
        //update killer inventory
        DataStorer.updateInventory(killer);
    }
}
