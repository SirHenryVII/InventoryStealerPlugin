package me.sirhenry.inventorystealer.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemSlot {

    public static ItemStack getItem(){
        //create item and get meta
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();

        //Set Display Name
        meta.setDisplayName("§aItem Slot");
        List<String> lore = meta.getLore();
        lore.add("§a Grants an Extra Item Slot");
        lore.add("§aRight Click to Use");

        item.setItemMeta(meta);

        return item;
    }

}
