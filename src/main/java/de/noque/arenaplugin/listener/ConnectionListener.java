package de.noque.arenaplugin.listener;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.ConfigManager;
import de.noque.arenaplugin.EditorData;
import de.noque.arenaplugin.StatsData;
import de.noque.arenaplugin.gui.SpawnGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        Bukkit.getScheduler().runTaskAsynchronously(ArenaPlugin.INSTANCE, () -> StatsData.load(player));
        Bukkit.getScheduler().runTaskAsynchronously(ArenaPlugin.INSTANCE, () -> EditorData.load(player));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " joined the server");

        ArenaPlugin.addToLobby(player);
        player.setGameMode(GameMode.SURVIVAL);
        new SpawnGUI(player);

        try {
            player.teleport(ConfigManager.getSpawn());
        } catch (NullPointerException ex) {
            player.sendMessage(ChatColor.RED + "Spawn isn't set yet.");
        }
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.RED + player.getName() + ChatColor.GRAY + " left the server");

        ArenaPlugin.removeFromKit(player);
        ArenaPlugin.removeFromLobby(player);
        ArenaPlugin.removeFromEditor(player);
        ArenaPlugin.resetBlocks();
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Player player = e.getPlayer();

        ArenaPlugin.removeFromKit(player);
        ArenaPlugin.removeFromLobby(player);
        ArenaPlugin.removeFromEditor(player);
        ArenaPlugin.resetBlocks();
    }
}
