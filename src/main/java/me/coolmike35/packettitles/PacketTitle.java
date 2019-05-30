package me.coolmike35.packettitles;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static me.coolmike35.packettitles.AutoMessage.*;

public class PacketTitle implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            tell(sender, ChatColor.RED + "Usage: /packettile {player} {message}");
        }else if(args.length == 1){
            tell(sender, ChatColor.RED + "Usage: /packettile {player} {message}");
        }else{
            Player target = Bukkit.getPlayer(args[0]);
            if(args[0].equals("*") || args[0].equals("@a")){
                for(Player people : Bukkit.getOnlinePlayers()){
                    target = people;
                }
            }
            if(target == null){
                tell(sender, ChatColor.RED + args[0] + " is not a player");
            }else{
                StringBuilder sb = new StringBuilder();
                for(int i = 1; i < args.length; i++){
                    sb.append(args[i] + " ");
                }
                String title = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());
                PlayerConnection connection = ((CraftPlayer) target.getPlayer()).getHandle().playerConnection;
                IChatBaseComponent text = IChatBaseComponent.ChatSerializer.a("{'text': '" + title + "'}");
                PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, text, 1, 5, 1);
                connection.sendPacket(packet);
                if(args[0].equals("*") || args[0].equals("@a")){
                    tell(sender, ChatColor.DARK_RED + "Everyone" + ChatColor.GREEN + " was sent the title," + ChatColor.RESET + " " + title);
                }else {
                    tell(sender, ChatColor.BLUE + target.getName() + ChatColor.GREEN + " was sent the title," + ChatColor.RESET + " " + title);
                }
            }
        }
        return true;
    }
}
