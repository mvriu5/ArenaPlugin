package de.noque.arenaplugin.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuildUHCInventory {

    public BuildUHCInventory(Player player) {
        getDefaultInv(player);
    }

    public static Inventory getDefaultInv(Player player) {

        player.getInventory().clear();

        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);

        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);


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


        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);

        player.getInventory().setItem(0, sword);
        player.getInventory().setItem(1, rod);
        player.getInventory().setItem(2, bow);
        player.getInventory().setItem(3, lava1);
        player.getInventory().setItem(4, lava2);
        player.getInventory().setItem(5, water1);
        player.getInventory().setItem(6, water2);
        player.getInventory().setItem(7, gap);
        player.getInventory().setItem(8, head);

        player.getInventory().setItem(9, pickaxe);
        player.getInventory().setItem(10, axe);
        player.getInventory().setItem(11, cobble);
        player.getInventory().setItem(12, wood);
        player.getInventory().setItem(13, arrow);
        player.getInventory().setItem(14, steak);

        return player.getInventory();
    }
}
