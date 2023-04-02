package me.sirhenry.inventorystealer;

import me.sirhenry.inventorystealer.items.InventoryBarrier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.json.JSONObject;

import java.io.*;

public class DataStorer {
    public static JSONObject InvList;
    private static File file;
    private static Plugin plugin = InventoryStealer.getPlugin(InventoryStealer.class);

    //init function
    public static void init(){
        //init file object
        file = new File(plugin.getDataFolder() +  "Inventories.json");

        //if file does not exist, make file and return
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            InvList = new JSONObject();
            return;
        }

        //Update Local JSON Object
        try{
            FileReader FR = new FileReader(file.getPath());
            String fileString = FR.toString();

            InvList = new JSONObject(fileString);
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }

    }

    public static void Save(){
        //Write File
        try{
            FileWriter FW = new FileWriter(file.getPath());
            FW.write(InvList.toString());
            FW.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void setBarrierNum(Player player, int num){
        InvList.put(player.getUniqueId().toString(), num);
        Save();
    }

    public static void updateInventory(Player player){
        Inventory inv = player.getInventory();

        //remove all barriers
        for(ItemStack item : inv){
            if(item.isSimilar(InventoryBarrier.getItem())){
                inv.remove(item);
            }
        }

        //add correct amount of barriers
        for(int i = 0; i < (int) InvList.get(player.getUniqueId().toString()); i++){
                inv.setItem(36-i, InventoryBarrier.getItem());
        }
    }

    public static int getBarrierNum(Player player){
        return (int) InvList.get(player.getUniqueId().toString());
    }
}
