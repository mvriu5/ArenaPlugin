package de.noque.arenaplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EditGUI {

    public EditGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Kit Editor");

        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        ItemMeta rodMeta = rod.getItemMeta();
        rodMeta.setDisplayName(ChatColor.GOLD + "Rod");
        rod.setItemMeta(rodMeta);
        gui.addItem(rod);

        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
        ItemMeta soupMeta = soup.getItemMeta();
        soupMeta.setDisplayName(ChatColor.GOLD + "Soup");
        soup.setItemMeta(soupMeta);
        gui.addItem(soup);

        ItemStack uhc = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta uhcMeta = uhc.getItemMeta();
        uhcMeta.setDisplayName(ChatColor.GOLD + "BuildUHC");
        uhc.setItemMeta(uhcMeta);
        gui.addItem(uhc);

        player.openInventory(gui);
    }
}
