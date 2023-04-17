package de.noque.arenaplugin.kits;

import de.noque.arenaplugin.ArenaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SoupInventory {

    public static Inventory getInv() {
        Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);

        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);

        ItemStack bm = new ItemStack(Material.BROWN_MUSHROOM);
        bm.setAmount(64);
        ItemStack rm = new ItemStack(Material.RED_MUSHROOM);
        rm.setAmount(64);
        ItemStack bowl = new ItemStack(Material.BOWL);
        bowl.setAmount(64);

        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.addEnchant(Enchantment.DURABILITY, 2, true);
        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        sword.setItemMeta(swordMeta);

        //Give items
        for (int i = 0; i < 36; i++) {
            inv.setItem(i, soup);
        }
        inv.setItem(0, sword);
        inv.setItem(13, bm);
        inv.setItem(14, rm);
        inv.setItem(15, bowl);

        return inv;
    }
}
