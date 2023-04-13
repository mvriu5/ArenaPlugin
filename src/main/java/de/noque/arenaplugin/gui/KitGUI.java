package de.noque.arenaplugin.gui;

import de.noque.arenaplugin.ArenaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class KitGUI {

    public KitGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Kit Selection");

        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        ItemMeta rodMeta = rod.getItemMeta();
        rodMeta.setDisplayName(ChatColor.GOLD + "Rod");
        ArrayList<String> rodLore = new ArrayList<>();
        rodLore.add(ChatColor.GREEN + "Players: " + Collections.frequency(ArenaPlugin.getKit().values(), "Rod"));
        rodMeta.setLore(rodLore);
        rod.setItemMeta(rodMeta);
        gui.addItem(rod);

        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
        ItemMeta soupMeta = soup.getItemMeta();
        soupMeta.setDisplayName(ChatColor.GOLD + "Soup");
        ArrayList<String> soupLore = new ArrayList<>();
        soupLore.add(ChatColor.GREEN + "Players: " + Collections.frequency(ArenaPlugin.getKit().values(), "Soup"));
        soupMeta.setLore(soupLore);
        soup.setItemMeta(soupMeta);
        gui.addItem(soup);

        ItemStack uhc = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta uhcMeta = uhc.getItemMeta();
        uhcMeta.setDisplayName(ChatColor.GOLD + "BuildUHC");
        ArrayList<String> uhcLore = new ArrayList<>();
        uhcLore.add(ChatColor.GREEN + "Players: " + Collections.frequency(ArenaPlugin.getKit().values(), "BuildUHC"));
        uhcMeta.setLore(uhcLore);
        uhc.setItemMeta(uhcMeta);
        gui.addItem(uhc);

        player.openInventory(gui);
    }
}
