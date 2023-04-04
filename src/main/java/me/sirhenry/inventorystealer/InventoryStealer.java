package me.sirhenry.inventorystealer;

import me.sirhenry.inventorystealer.Commands.InventorySlotCommands;
import me.sirhenry.inventorystealer.Commands.TabCompleters.InventorySlotTabCompleter;
import me.sirhenry.inventorystealer.Commands.TabCompleters.WithdrawTabCompleter;
import me.sirhenry.inventorystealer.Commands.WithdrawCommand;
import me.sirhenry.inventorystealer.Listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryStealer extends JavaPlugin {

    @Override
    public void onEnable() {
        //Config Init
        saveDefaultConfig();
        Config.init();

        //Data Storer Init
        DataStorer.init();

        //Register Events
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new ItemInteractListener(), this);
        getServer().getPluginManager().registerEvents(new GuiInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSwapHandsListener(), this);


        //Register Commands
        this.getCommand("withdraw").setExecutor(new WithdrawCommand());
        this.getCommand("inventoryslots").setExecutor(new InventorySlotCommands());

        //Set Tab Completers
        this.getCommand("withdraw").setTabCompleter(new WithdrawTabCompleter());
        this.getCommand("inventoryslots").setTabCompleter(new InventorySlotTabCompleter());


    }

    @Override
    public void onDisable() {

    }
}
