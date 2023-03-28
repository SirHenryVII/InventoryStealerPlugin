package me.sirhenry.inventorystealer;

import org.bukkit.entity.Player;
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
}
