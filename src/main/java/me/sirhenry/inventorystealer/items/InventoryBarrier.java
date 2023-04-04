package me.sirhenry.inventorystealer.items;

import me.sirhenry.inventorystealer.InventoryStealer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class InventoryBarrier {

    public static ItemStack getItem(){
        //create item and get meta
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();

        //set Display name
        meta.setDisplayName("§c§lLOCKED SLOT");

        //set lore
        List<String> lore = new ArrayList<>();
        lore.add("§cThis Slot is Locked!");
        meta.setLore(lore);

        //set meta
        item.setItemMeta(meta);

        //return item
        return item;
    }
}
