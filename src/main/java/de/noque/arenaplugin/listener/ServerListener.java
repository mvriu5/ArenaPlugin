package de.noque.arenaplugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.util.CachedServerIcon;

import java.io.File;

public class ServerListener implements Listener {

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {

        e.setMotd(
                ChatColor.GOLD + "NOQUE'S ARENA FFA " +
                ChatColor.GRAY + "[" + ChatColor.YELLOW + "1.7.10" + ChatColor.GRAY + "]\n" +
                ChatColor.YELLOW + "» " + ChatColor.GREEN + ChatColor.BOLD + "Kit Update" + ChatColor.RESET + ChatColor.YELLOW + " «");

        try {
            CachedServerIcon icon = Bukkit.loadServerIcon(new File("icon.png"));
            e.setServerIcon(icon);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        e.setMaxPlayers(0);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.getWorld().setStorm(false);
        e.getWorld().setThundering(false);
        e.setCancelled(true);
    }
}
