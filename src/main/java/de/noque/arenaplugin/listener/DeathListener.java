package de.noque.arenaplugin.listener;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.ConfigManager;
import de.noque.arenaplugin.EditorData;
import de.noque.arenaplugin.StatsData;
import de.noque.arenaplugin.gui.SpawnGUI;
import de.noque.arenaplugin.kits.DamagerInventory;
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
import org.bukkit.inventory.Inventory;
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
        }

        //Wenn Spieler im FFA ist
        if (!ArenaPlugin.getKit().get(player.getUniqueId()).equals("Damager")) {
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
        }

        //Wenn Spieler in FFA ist
        if (!ArenaPlugin.getKit().get(player.getUniqueId()).equals("Damager")) {

            if (player != killer) {
                //Firework Effect
                FireworkEffect fireworkEffect = FireworkEffect.builder().flicker(true).trail(true).with(BALL).withColor(Color.ORANGE).withFade(Color.GREEN).build();
                new InstantFirework(fireworkEffect, player.getLocation());

                //Message
                double health = Math.round(killer.getHealth() * 100) / 100.0;
                e.setDeathMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " was slain by " + ChatColor.GREEN + killer.getName() + ChatColor.GRAY + " (" + ChatColor.RED + health/2 + "❤" + ChatColor.GRAY + ")");
                killer.setHealth(20.0);

                //Give kit to killer
                killer.setHealth(20.0);
                switch (ArenaPlugin.getKit().get(killer.getUniqueId())) {
                    case "Rod":
                        Inventory rodInv = EditorData.getInventory(killer, "Rod");
                        killer.getInventory().clear();
                        killer.getInventory().addItem(rodInv.getContents());
                        break;
                    case "Soup":
                        Inventory soupInv = EditorData.getInventory(killer, "Soup");
                        killer.getInventory().clear();
                        killer.getInventory().addItem(soupInv.getContents());
                        break;
                    case "BuildUHC":
                        Inventory buildInv = EditorData.getInventory(killer, "BuildUHC");
                        killer.getInventory().clear();
                        killer.getInventory().addItem(buildInv.getContents());
                        break;
                    default:
                        break;
                }

                //Update stats
                StatsData.addDeath(player);
                StatsData.addKill(killer);
            }

            if (player == killer) {
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
