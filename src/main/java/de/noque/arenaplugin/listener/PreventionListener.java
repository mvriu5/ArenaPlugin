package de.noque.arenaplugin.listener;

import de.noque.arenaplugin.ArenaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import static org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.*;

public class PreventionListener implements Listener {

    private ArenaPlugin plugin;
    public PreventionListener(ArenaPlugin plugin) {
        this.plugin = plugin;
    }

     @EventHandler
     public void onBlockPlace(BlockPlaceEvent e) {
         Player player = e.getPlayer();

         if (!(player.getGameMode() == GameMode.CREATIVE)) {
             e.setCancelled(true);
         }
         if (ArenaPlugin.getKit().get(player.getUniqueId()).equals("BuildUHC")) {
             e.setCancelled(false);

             if (e.getBlock().getY() > 28) {
                 e.setCancelled(true);
             }

             ArenaPlugin.getNewBlocks().add(e.getBlock());
         }
     }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if (!(player.getGameMode() == GameMode.CREATIVE)) {
            e.setCancelled(true);
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

        if (ArenaPlugin.getLobby().contains(player.getUniqueId())) {
            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                e.setCancelled(true);
            }
        }

        final Item item = e.getItemDrop();

        //Schedule remove of the item in 15 Seconds.
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if(item != null && !item.isDead() && item.isValid()){
                item.remove();
            }
        }, 15 * 20);
    }


    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
         Player player = e.getPlayer();

        if (ArenaPlugin.getLobby().contains(player.getUniqueId())) {
            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onRegainHealth(EntityRegainHealthEvent e) {
        Player player = (Player) e.getEntity();

        if (ArenaPlugin.getKit().get(player.getUniqueId()).equals("BuildUHC") && (e.getRegainReason() == SATIATED || e.getRegainReason() == REGEN)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}
