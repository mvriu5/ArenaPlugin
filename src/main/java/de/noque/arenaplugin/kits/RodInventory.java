package de.noque.arenaplugin.kits;

import de.noque.arenaplugin.ArenaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class RodInventory {

    public static Inventory getInv() {
        Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();
        sword.setItemMeta(swordMeta);

        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        ItemMeta rodMeta = rod.getItemMeta();
        rod.setItemMeta(rodMeta);

        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

        inv.setItem(0, sword);
        inv.setItem(1, rod);
        inv.setItem(2, helmet);
        inv.setItem(3, chestplate);
        inv.setItem(4, leggings);
        inv.setItem(5, boots);

        return inv;
    }
}
