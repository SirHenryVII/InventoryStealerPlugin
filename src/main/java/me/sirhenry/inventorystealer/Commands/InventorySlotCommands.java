package me.sirhenry.inventorystealer.Commands;

import me.sirhenry.inventorystealer.DataStorer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventorySlotCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //check permissions
        if(!sender.hasPermission(command.getPermission())){
            sender.sendMessage("§cYou do Not have the Required Permissions to Run this Command.");
            return true;
        }

        //Check Correct number of Args
        if(args.length != 3) return false;

        //Try to get Player
        Player player = Bukkit.getPlayer(args[0]);
        if(player == null) return false;

        //try to parse num
        int num;
        try{
            num = Integer.parseInt(args[2]);
        }catch(NumberFormatException ex){
            return false;
        }

        //set command
        if(args[1].equals("set")){
            if((36 - num) > 35 || (36 - num) < 0){
                sender.sendMessage("§cThis Action Cannot be Performed, please Check your Number of Inventory Slots Again.");
                return true;
            }
            DataStorer.setBarrierNum(player, 36 - num);
            DataStorer.updateInventory(player);
            return true;
        }

        //Add Command
        else if(args[1].equals("add")){
            if(DataStorer.getBarrierNum(player) - num >= 36 || DataStorer.getBarrierNum(player) - num <= 0){
                sender.sendMessage("§cThis Action Cannot be Performed, please Check your Number of Inventory Slots Again.");
                return true;
            }
            DataStorer.setBarrierNum(player, DataStorer.getBarrierNum(player) - num);
            DataStorer.updateInventory(player);
            return true;
        }

        //Subtract Command
        else if(args[1].equals("subtract")){
            if(DataStorer.getBarrierNum(player) + num >= 36 || DataStorer.getBarrierNum(player) + num <= 0){
                sender.sendMessage("§cThis Action Cannot be Performed, please Check your Number of Inventory Slots Again.");
                return true;
            }
            DataStorer.setBarrierNum(player, DataStorer.getBarrierNum(player) + num);
            DataStorer.updateInventory(player);
            return true;
        }

        return false;
    }
}
