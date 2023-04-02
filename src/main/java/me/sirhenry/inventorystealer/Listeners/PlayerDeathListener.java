package me.sirhenry.inventorystealer.Listeners;

import me.sirhenry.inventorystealer.DataStorer;
import me.sirhenry.inventorystealer.items.InventoryBarrier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class PlayerDeathListener implements Listener {

    @EventHandler

    public void onPlayerDeath(PlayerDeathEvent e){

        Player player = e.getEntity();

        //make it so that player doesn't drop barriers

        for(ItemStack item : e.getDrops()){
            if(item.isSimilar(InventoryBarrier.getItem())){
                e.getDrops().remove(item);
            }
        }

        //if died of natural causes, return
        if(player.getKiller() == null){
            return;
        }

        Player killer = player.getKiller();
        int playerBarrierNum = DataStorer.getBarrierNum(player);
        int killerBarrierNum = DataStorer.getBarrierNum(killer);

        //Give slot to killer, remove one from victim
        DataStorer.setBarrierNum(player, playerBarrierNum + 1);
        DataStorer.setBarrierNum(killer, killerBarrierNum - 1);

        //victim will respawn so no need to update inventory
        //update killer inventory
        DataStorer.updateInventory(killer);
    }
}
