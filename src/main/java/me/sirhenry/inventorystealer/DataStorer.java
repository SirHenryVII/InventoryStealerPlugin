package me.sirhenry.inventorystealer;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.sirhenry.inventorystealer.items.InventoryBarrier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class DataStorer {
    public static JSONObject InvList;
    public static JSONObject DeadList;
    private static File InvFile;
    private static File DeadFile;
    private static Plugin plugin = InventoryStealer.getPlugin(InventoryStealer.class);

    //init function
    public static void init(){
        //init file object
        InvFile = new File(plugin.getDataFolder(), "Inventories.json");
        DeadFile = new File(plugin.getDataFolder(), "Dead.json");

        //create files if they haven't been
        try{

            if(!InvFile.exists()){
                InvFile.createNewFile();
                InvList = new JSONObject();
                if(DeadFile.exists()){
                    return;
                }
            }
            if(!DeadFile.exists()){
                DeadFile.createNewFile();
                DeadList = new JSONObject();
                return;
            }

        }catch(IOException ex){
        ex.printStackTrace();
        }


        //Update Local JSON Object
        try{
            Scanner reader = new Scanner(InvFile);

            StringBuilder fileString = new StringBuilder();
            while(reader.hasNextLine()){
                fileString.append(reader.nextLine());
            }

            reader.close();

            //make sure file isn't empty
            if(fileString.toString().equals("")){
                InvList = new JSONObject();
            }
            else{
                InvList = new JSONObject(fileString.toString());
            }

        } catch(IOException ex){
            ex.printStackTrace();
        }

        //repeat for dead list
        try{
            Scanner reader = new Scanner(DeadFile);

            StringBuilder fileString = new StringBuilder();
            while(reader.hasNextLine()){
                fileString.append(reader.nextLine());
            }

            reader.close();

            //make sure file isn't empty
            if(fileString.toString().equals("")){
                DeadList = new JSONObject();
            }
            else{
                DeadList = new JSONObject(fileString.toString());
            }

        } catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public static void addDead(Player player){
        DeadList.put(player.getUniqueId().toString(), 1);
        saveDeadList();
    }

    public static Boolean checkDead(Player player){
        if(DeadList.has(player.getUniqueId().toString())){
            return true;
        }
        return false;
    }

    public static void saveDeadList(){
        //Write File
        try{
            FileWriter FW = new FileWriter(DeadFile.getPath());
            FW.write(DeadList.toString());
            FW.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void saveInvList(){
        //Write File
        try{
            FileWriter FW = new FileWriter(InvFile.getPath());
            FW.write(InvList.toString());
            FW.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void setBarrierNum(Player player, int num){
        InvList.put(player.getUniqueId().toString(), num);
        saveInvList();
    }

    public static void updateInventory(Player player){
        Inventory inv = player.getInventory();

        //remove all barriers
        inv.remove(InventoryBarrier.getItem());

        //add correct amount of barriers
        int[] SlotOrder = {9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        for(int i = 0; i < (int) InvList.get(player.getUniqueId().toString()); i++){
                //make sure to drop items so that no items get deleted
                if(inv.getItem(SlotOrder[i]) != null && !inv.getItem(SlotOrder[i]).isSimilar(InventoryBarrier.getItem())){
                    player.getWorld().dropItem(player.getLocation(), inv.getItem(SlotOrder[i]));
                }
                inv.setItem(SlotOrder[i], InventoryBarrier.getItem());
        }
    }

    public static int getBarrierNum(Player player){
        return (int) InvList.get(player.getUniqueId().toString());
    }
}
