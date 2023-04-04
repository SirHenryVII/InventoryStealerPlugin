package me.sirhenry.inventorystealer.Commands.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class InventorySlotTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if(args.length == 1){
            return null;
        }
        else if(args.length == 2){
            list.add("set");
            list.add("add");
            list.add("subtract");
        }
        return list;
    }
}
