package de.noque.arenaplugin.listener;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.ConfigManager;
import de.noque.arenaplugin.PlayerStats;
import de.noque.arenaplugin.gui.SpawnGUI;
import de.noque.arenaplugin.kits.BuildUHCInventory;
import de.noque.arenaplugin.kits.DamagerInventory;
import de.noque.arenaplugin.kits.RodInventory;
import de.noque.arenaplugin.kits.SoupInventory;
import de.noque.arenaplugin.util.InstantFirework;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.FireworkEffect.Type.BALL;

public class DeathListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();

        //Wenn Spieler im Damager ist
        if (ArenaPlugin.getKit().get(player.getUniqueId()).equals("Damager")) {
            new DamagerInventory(player);

            try {
                e.setRespawnLocation(ConfigManager.getDamager());
            } catch (NullPointerException ex) {
                player.sendMessage(ChatColor.RED + "Spawn isn't set yet.");
            }

        //Wenn Spieler im FFA ist
        } else {
            new SpawnGUI(player);
            ArenaPlugin.removeFromKit(player);
            ArenaPlugin.addToLobby(player);

            try {
                e.setRespawnLocation(ConfigManager.getSpawn());
            } catch (NullPointerException ex) {
                player.sendMessage(ChatColor.RED + "Spawn isn't set yet.");
            }
        }
    }


    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = player.getKiller();

        //Wenn Spieler im Damager ist
        if (ArenaPlugin.getKit().get(player.getUniqueId()).equals("Damager")) {
            e.setDeathMessage("");

            //Wenn Spieler in FFA ist
        } else {

            if (player != killer) {

                //Firework Effect
                FireworkEffect fireworkEffect = FireworkEffect.builder().flicker(true).trail(true).with(BALL).withColor(Color.ORANGE).withFade(Color.GREEN).build();
                new InstantFirework(fireworkEffect, player.getLocation());

                //Message
                double health = Math.round(killer.getHealth() * 100) / 100.0;
                e.setDeathMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " was slain by " + ChatColor.GREEN + killer.getName() +
                        ChatColor.GRAY + " (" + ChatColor.RED + health/2 + "❤" + ChatColor.GRAY + ")");
                killer.setHealth(20.0);

                //Give kit to killer
                killer.setHealth(20.0);
                switch (ArenaPlugin.getKit().get(killer.getUniqueId())) {
                    case "Rod": new RodInventory(killer);
                        break;
                    case "Soup": new SoupInventory(killer);
                        break;
                    case "BuildUHC": new BuildUHCInventory(killer);
                        break;
                    default:
                        break;
                }

                //Stats
                PlayerStats.addDeath(player);
                PlayerStats.addKill(killer);

            } else {
                e.setDeathMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " killed his Doppelgänger.");
            }
        }

        ArenaPlugin.resetBlocks();

        //Remove Drops
        for(ItemStack i : e.getDrops()) {
            i.setType(Material.AIR);
        }
    }
}
