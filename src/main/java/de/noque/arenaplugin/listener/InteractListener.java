package de.noque.arenaplugin.listener;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.gui.EditGUI;
import de.noque.arenaplugin.gui.KitGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.potion.PotionEffectType.REGENERATION;

public class InteractListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Material material = player.getInventory().getItemInHand().getType();

        //KIT SELECTOR
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && material == Material.CHEST) {
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1.0F, 0.5F);
            new KitGUI(player);
        }

        //KIT EDITOR
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && material == Material.BOOK) {
            player.playSound(player.getLocation(), Sound.CLICK, 1.0F, 0.5F);
            new EditGUI(player);
        }

        //SOUP
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && material == Material.MUSHROOM_SOUP
                && (ArenaPlugin.getKit().get(player.getUniqueId()).equals("Soup") || ArenaPlugin.getKit().get(player.getUniqueId()).equals("Damager"))) {
            double health = player.getHealth();

            if (health < 14.0) {
                player.setHealth(health + 6.0);
                player.getInventory().getItemInHand().setType(Material.BOWL);
            } else if (health >= 14.0 && health < 20.0) {
                player.setHealth(20.0);
                player.getInventory().getItemInHand().setType(Material.BOWL);
            }
        }

        //Disable Water/Lava Placement
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (material == Material.WATER_BUCKET || material == Material.LAVA_BUCKET)) {
            if (e.getClickedBlock().getY() > 28 || player.getLocation().getY() > 28) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();

        if (e.getItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Golden Head")) {
            player.addPotionEffect(new PotionEffect(REGENERATION, 10*20, 1));
        }

    }
}
