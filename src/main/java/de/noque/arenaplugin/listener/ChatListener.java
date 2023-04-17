package de.noque.arenaplugin.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player player = e.getPlayer();

        if (player.isOp()) {
            e.setFormat(ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + e.getMessage());
        }
        if (!player.isOp()) {
            e.setFormat(ChatColor.GREEN + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + e.getMessage());
        }
    }
}
