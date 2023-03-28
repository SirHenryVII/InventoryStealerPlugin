package me.sirhenry.inventorystealer;

import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryStealer extends JavaPlugin {

    @Override
    public void onEnable() {
        //Config Init
        saveDefaultConfig();
        Config.init();

        //Data Storer Init
        DataStorer.init();

    }

    @Override
    public void onDisable() {

    }
}
