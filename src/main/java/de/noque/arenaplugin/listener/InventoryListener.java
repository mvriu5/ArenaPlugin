package de.noque.arenaplugin.listener;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.EditorData;
import de.noque.arenaplugin.kits.BuildUHCInventory;
import de.noque.arenaplugin.kits.RodInventory;
import de.noque.arenaplugin.kits.SoupInventory;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(false);

        //Cancel item dragging
        if (ArenaPlugin.getLobby().contains(player.getUniqueId()) || player.getGameMode().equals(GameMode.SURVIVAL)) {
            e.setCancelled(true);
        }

        //KIT SELECTION
        if (e.getView().getTitle().contains("Kit Selection") && e.getInventory() != null && e.getCurrentItem().hasItemMeta()) {

            switch(e.getCurrentItem().getType()) {
                case FISHING_ROD:
                    ArenaPlugin.addToKit(player, "Rod");
                    EditorData.getData(player, "Rod");
                    break;
                case MUSHROOM_SOUP:
                    ArenaPlugin.addToKit(player, "Soup");
                    EditorData.getData(player, "Rod");
                    break;
                case LAVA_BUCKET:
                    ArenaPlugin.addToKit(player, "BuildUHC");
                    EditorData.getData(player, "Rod");
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
                    EditorData.getData(player, "Rod");
                    break;
                case MUSHROOM_SOUP:
                    ArenaPlugin.addToEditor(player, "Soup");
                    EditorData.getData(player, "Soup");
                    break;
                case LAVA_BUCKET:
                    ArenaPlugin.addToEditor(player, "BuildUHC");
                    EditorData.getData(player, "BuildUHC");
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
