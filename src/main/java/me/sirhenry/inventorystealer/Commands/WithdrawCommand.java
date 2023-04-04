package me.sirhenry.inventorystealer.Commands;

import me.sirhenry.inventorystealer.DataStorer;
import me.sirhenry.inventorystealer.items.ItemSlot;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WithdrawCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //make sure not console
        if(!(sender instanceof Player)){
            sender.sendMessage("Only Players can Run this Command!");
            return true;
        }

        //check permissions
        if(!sender.hasPermission(command.getPermission())){
            sender.sendMessage("§cYou do not Have the Required Permissions to Run this Command.");
            return true;
        }

        //check num of args
        if(args.length != 1) return false;

        //try to parse slot num
        int slotNum;
        try{
            slotNum = Integer.parseInt(args[0]);
        }catch(NumberFormatException ex){
            return false;
        }

        //Make sure slotNum is not Negative
        if(slotNum < 0){
            sender.sendMessage("§cYou Cannot Use Negative Numbers.");
        }

        //make sure player can afford to lose that many inventory slots
        if(DataStorer.getBarrierNum((Player) sender) + slotNum > 35){
            sender.sendMessage("§cYou do not have Enough Inventory Slots to Perform this Action.");
            return true;
        }

        //update inventory
        DataStorer.setBarrierNum((Player) sender, DataStorer.getBarrierNum((Player) sender) + slotNum);
        DataStorer.updateInventory((Player) sender);

        //give inventory slots
        ItemStack slots = ItemSlot.getItem();
        slots.setAmount(slotNum);

        //if player's inventory is full, drop item instead
        if(((Player) sender).getInventory().firstEmpty() == -1){
            ((Player) sender).getWorld().dropItem(((Player) sender).getLocation(), slots);
        }else{
            ((Player) sender).getInventory().addItem(slots);
        }

        sender.sendMessage("§aYou just Withdrew " + slotNum + " Inventory Slots!");

        return true;
    }
}
