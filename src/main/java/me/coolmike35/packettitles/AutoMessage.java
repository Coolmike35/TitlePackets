package me.coolmike35.packettitles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AutoMessage {
    public static void tell(CommandSender sender, String message) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(message);
        } else {
            Bukkit.getConsoleSender().sendMessage(message);
        }
    }
}
