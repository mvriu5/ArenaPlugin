package de.noque.arenaplugin.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.potion.PotionEffectType.*;

public class SpawnGUI {

    public SpawnGUI(Player player) {

        player.getInventory().clear();
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.removePotionEffect(ABSORPTION);
        player.removePotionEffect(DAMAGE_RESISTANCE);
        player.removePotionEffect(REGENERATION);
        player.setFireTicks(0);

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        //KIT SELECTOR
        ItemStack chest = new ItemStack(Material.CHEST);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setDisplayName(ChatColor.GOLD + "KIT SELECTOR " + ChatColor.GRAY + "(Right Click)");
        chest.setItemMeta(chestMeta);
        player.getInventory().setItem(4, chest);

        //KIT EDITOR
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta bookMeta = book.getItemMeta();
        bookMeta.setDisplayName(ChatColor.GOLD + "KIT EDITOR " + ChatColor.GRAY + "(Right Click)");
        book.setItemMeta(bookMeta);
        player.getInventory().setItem(4, book);

    }
}
