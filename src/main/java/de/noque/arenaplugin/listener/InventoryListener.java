package de.noque.arenaplugin.listener;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.EditorData;
import de.noque.arenaplugin.kits.BuildUHCInventory;
import de.noque.arenaplugin.kits.RodInventory;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.potion.PotionEffectType.DAMAGE_RESISTANCE;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);

        if (ArenaPlugin.getEditor().containsKey(player.getUniqueId()) || ArenaPlugin.getKit().containsKey(player.getUniqueId())) {
            e.setCancelled(false);
        }

        //KIT SELECTION
        if (e.getView().getTitle().contains("Kit Selection") && e.getInventory() != null && e.getCurrentItem().hasItemMeta()) {

            switch(e.getCurrentItem().getType()) {
                case FISHING_ROD:
                    ArenaPlugin.addToKit(player, "Rod");
                    Inventory rodInv = EditorData.getInventory(player, "Rod");
                    player.getInventory().clear();
                    player.getInventory().setContents(rodInv.getContents());
                    RodInventory.getArmor(player);
                    break;
                case MUSHROOM_SOUP:
                    ArenaPlugin.addToKit(player, "Soup");
                    Inventory soupInv = EditorData.getInventory(player, "Soup");
                    player.addPotionEffect(new PotionEffect(DAMAGE_RESISTANCE, 99999,2));
                    player.getInventory().clear();
                    player.getInventory().setContents(soupInv.getContents());
                    break;
                case LAVA_BUCKET:
                    ArenaPlugin.addToKit(player, "BuildUHC");
                    Inventory buildInv = EditorData.getInventory(player, "BuildUHC");
                    player.getInventory().clear();
                    player.getInventory().setContents(buildInv.getContents());
                    BuildUHCInventory.getArmor(player);
                    break;
                default:
                    break;
            }
            ArenaPlugin.removeFromLobby(player);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 0.1F, 0.5F);
            player.setGameMode(GameMode.SURVIVAL);
            player.closeInventory();
        }

        //KIT EDITOR
        if (e.getView().getTitle().contains("Kit Editor") && e.getInventory() != null && e.getCurrentItem().hasItemMeta()) {

            switch(e.getCurrentItem().getType()) {
                case FISHING_ROD:
                    ArenaPlugin.addToEditor(player, "Rod");
                    Inventory rodInv = EditorData.getInventory(player, "Rod");
                    player.getInventory().clear();
                    player.getInventory().setContents(rodInv.getContents());
                    break;
                case MUSHROOM_SOUP:
                    ArenaPlugin.addToEditor(player, "Soup");
                    Inventory soupInv = EditorData.getInventory(player, "Soup");
                    player.getInventory().clear();
                    player.getInventory().setContents(soupInv.getContents());
                    break;
                case LAVA_BUCKET:
                    ArenaPlugin.addToEditor(player, "BuildUHC");
                    Inventory buildInv = EditorData.getInventory(player, "BuildUHC");
                    player.getInventory().clear();
                    player.getInventory().setContents(buildInv.getContents());
                    break;
                default:
                    break;
            }
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 0.1F, 0.5F);
            player.sendMessage(ChatColor.GREEN + "You can now edit the kit.");
            player.sendMessage(ChatColor.GREEN + "Save your kit with " + ChatColor.YELLOW + "/save");
            player.setGameMode(GameMode.SURVIVAL);
            player.closeInventory();
        }
    }
}
