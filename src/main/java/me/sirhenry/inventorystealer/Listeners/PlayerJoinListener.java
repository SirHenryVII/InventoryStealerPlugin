package me.sirhenry.inventorystealer.Listeners;

import me.sirhenry.inventorystealer.Config;
import me.sirhenry.inventorystealer.DataStorer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        //if player has never joined before, set inv slots to Default Values
        if(!DataStorer.InvList.has(e.getPlayer().getUniqueId().toString())){
            DataStorer.updateInventory(e.getPlayer(), Config.getDefaultHearts());
        }
    }
}