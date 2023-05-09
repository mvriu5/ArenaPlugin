package de.noque.arenaplugin.listener;

import de.noque.arenaplugin.ArenaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import static org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.REGEN;
import static org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.SATIATED;

public class PreventionListener implements Listener {

    private final ArenaPlugin plugin;
    public PreventionListener(ArenaPlugin plugin) {
        this.plugin = plugin;
    }

     @EventHandler
     public void onBlockPlace(BlockPlaceEvent e) {
         Player player = e.getPlayer();
         e.setCancelled(true);

         if (player.getGameMode() == GameMode.CREATIVE) {
             e.setCancelled(false);
         }

         if (ArenaPlugin.getKit().get(player.getUniqueId()).equals("BuildUHC") && e.getBlock().getY() < 29) {
             e.setCancelled(false);
             ArenaPlugin.getNewBlocks().add(e.getBlock());
         }
     }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        e.setCancelled(true);

        if (player.getGameMode() == GameMode.CREATIVE) {
            e.setCancelled(false);
        }
        if (ArenaPlugin.getKit().get(player.getUniqueId()).equals("BuildUHC") && ArenaPlugin.getNewBlocks().contains(e.getBlock())) {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onBlockChange(BlockFromToEvent e) {
        ArenaPlugin.getNewBlocks().add(e.getToBlock());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        e.setCancelled(true);

        if (ArenaPlugin.getKit().containsKey(player.getUniqueId())) {
            e.setCancelled(false);
        }

        //Schedule remove of the item in 15 Seconds.
        final Item item = e.getItemDrop();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if(item != null && !item.isDead() && item.isValid()) {
                item.remove();
            }
        }, 15 * 20);
    }


    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
         Player player = e.getPlayer();
         e.setCancelled(true);

        if (!ArenaPlugin.getLobby().contains(player.getUniqueId()) && player.getGameMode().equals(GameMode.SURVIVAL)) {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);

        if (ArenaPlugin.getKit().get(player.getUniqueId()).equals("Soup") || ArenaPlugin.getKit().get(player.getUniqueId()).equals("Damager")) {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onRegainHealth(EntityRegainHealthEvent e) {
        Player player = (Player) e.getEntity();
        e.setCancelled(false);

        if (ArenaPlugin.getKit().get(player.getUniqueId()).equals("BuildUHC") && (e.getRegainReason() == SATIATED || e.getRegainReason() == REGEN)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}
