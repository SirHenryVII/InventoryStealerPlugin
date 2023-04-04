package me.sirhenry.inventorystealer;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    public static FileConfiguration Config;

    public static void init(){
        Config = InventoryStealer.getPlugin(InventoryStealer.class).getConfig();

        Config.addDefault("DefaultBarrierSlots", 18);
        Config.addDefault("LoseSlotWhenNotKilledByPlayer", true);
        Config.addDefault("BanOnDeath", false);
    }

    public static int getDefaultBarrierSlots(){
        return Config.getInt("DefaultBarrierSlots");
    }
    public static boolean getLoseSlotWhenNotKilledByPlayer() {
        return Config.getBoolean("LoseSlotWhenNotKilledByPlayer");
    }

    public static Boolean getBanOnDeath(){
        return Config.getBoolean("BanOnDeath");
    }
}
