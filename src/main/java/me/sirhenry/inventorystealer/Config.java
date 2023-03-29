package me.sirhenry.inventorystealer;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    public static FileConfiguration Config;

    public static void init(){
        Config = InventoryStealer.getPlugin(InventoryStealer.class).getConfig();

        Config.addDefault("DefaultInventorySlots", 18);
    }

    public static int getDefaultHearts(){
        return Config.getInt("DefaultInventorySlots");
    }
}
