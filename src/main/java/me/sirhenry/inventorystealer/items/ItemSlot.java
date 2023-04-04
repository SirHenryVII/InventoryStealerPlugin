package me.sirhenry.inventorystealer.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemSlot {

    public static ItemStack getItem(){
        //create item and get meta
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();

        //Set Display Name
        meta.setDisplayName("§a§lItem Slot");
        List<String> lore = new ArrayList();
        lore.add("§aGrants an Extra Item Slot");
        lore.add("§aRight Click to Use");
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

}
