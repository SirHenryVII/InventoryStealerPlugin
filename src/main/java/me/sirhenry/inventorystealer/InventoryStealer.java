package me.sirhenry.inventorystealer;

import me.sirhenry.inventorystealer.Listeners.PlayerJoinListener;
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

    }

    @Override
    public void onDisable() {

    }
}
