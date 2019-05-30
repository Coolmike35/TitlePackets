package me.coolmike35.packettitles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class PacketTitles extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[PacketTitles] Started!");
        getCommand("packettitle").setExecutor(new PacketTitle());
    }
}
