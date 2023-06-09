package de.noque.arenaplugin.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuildUHCInventory {

    public static Inventory getInv() {
        Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);

        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3);

        ItemStack steak = new ItemStack(Material.COOKED_BEEF);
        steak.setAmount(64);

        ItemStack cobble = new ItemStack(Material.COBBLESTONE);
        cobble.setAmount(64);

        ItemStack wood = new ItemStack(Material.WOOD);
        wood.setAmount(64);

        ItemStack arrow = new ItemStack(Material.ARROW);
        arrow.setAmount(16);


        ItemStack rod = new ItemStack((Material.FISHING_ROD));
        ItemStack pickaxe = new ItemStack((Material.DIAMOND_PICKAXE));
        ItemStack axe = new ItemStack((Material.DIAMOND_AXE));

        ItemStack water1 = new ItemStack(Material.WATER_BUCKET);
        ItemStack water2 = new ItemStack(Material.WATER_BUCKET);

        ItemStack lava1 = new ItemStack(Material.LAVA_BUCKET);
        ItemStack lava2 = new ItemStack(Material.LAVA_BUCKET);


        ItemStack gap = new ItemStack(Material.GOLDEN_APPLE);
        gap.setAmount(6);

        ItemStack head = new ItemStack(Material.GOLDEN_APPLE);
        head.setAmount(3);
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName(ChatColor.GOLD + "Golden Head");
        head.setItemMeta(headMeta);

        inv.setItem(0, sword);
        inv.setItem(1, rod);
        inv.setItem(2, bow);
        inv.setItem(3, lava1);
        inv.setItem(4, lava2);
        inv.setItem(5, water1);
        inv.setItem(6, water2);
        inv.setItem(7, gap);
        inv.setItem(8, head);

        inv.setItem(9, pickaxe);
        inv.setItem(10, axe);
        inv.setItem(11, cobble);
        inv.setItem(12, wood);
        inv.setItem(13, arrow);
        inv.setItem(14, steak);

        return inv;
    }

    public static void getArmor(Player player) {
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);

        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
    }
}
