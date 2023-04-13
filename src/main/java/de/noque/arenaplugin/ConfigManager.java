package de.noque.arenaplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ConfigManager {

    public static Location getSpawn() {
        return new Location(Bukkit.getWorld(ArenaPlugin.config.getString("spawn.world")),
                (double)(ArenaPlugin.config.get("spawn.x")),
                (double) ArenaPlugin.config.get("spawn.y"),
                (double) ArenaPlugin.config.get("spawn.z"),
                (float) ArenaPlugin.config.getDouble("spawn.yaw"),
                (float) ArenaPlugin.config.getDouble("spawn.pitch"));
    }

    public static void setSpawn(Location location) {
        ArenaPlugin.config.set("spawn.world", location.getWorld().getName());
        ArenaPlugin.config.set("spawn.x", location.getX());
        ArenaPlugin.config.set("spawn.y", location.getY());
        ArenaPlugin.config.set("spawn.z", location.getZ());
        ArenaPlugin.config.set("spawn.yaw", location.getYaw());
        ArenaPlugin.config.set("spawn.pitch", location.getPitch());
        ArenaPlugin.INSTANCE.saveConfig();
    }

    public static Location getDamager() {
        return new Location(Bukkit.getWorld(ArenaPlugin.config.getString("damager.world")),
                (double)(ArenaPlugin.config.get("damager.x")),
                (double) ArenaPlugin.config.get("damager.y"),
                (double) ArenaPlugin.config.get("damager.z"),
                (float) ArenaPlugin.config.getDouble("damager.yaw"),
                (float) ArenaPlugin.config.getDouble("damager.pitch"));
    }

    public static void setDamager(Location location) {
        ArenaPlugin.config.set("damager.world", location.getWorld().getName());
        ArenaPlugin.config.set("damager.x", location.getX());
        ArenaPlugin.config.set("damager.y", location.getY());
        ArenaPlugin.config.set("damager.z", location.getZ());
        ArenaPlugin.config.set("damager.yaw", location.getYaw());
        ArenaPlugin.config.set("damager.pitch", location.getPitch());
        ArenaPlugin.INSTANCE.saveConfig();
    }
}