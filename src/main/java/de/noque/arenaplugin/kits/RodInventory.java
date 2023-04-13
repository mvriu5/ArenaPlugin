package de.noque.arenaplugin.kits;

import com.fren_gor.invManagementPlugin.api.itemStack.ItemStackSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class RodInventory {


    public RodInventory(Player player) {
        getDefaultInv(player);
    }

    public static Inventory getDefaultInv(Player player) {
        player.getInventory().clear();

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

        player.getInventory().setItem(0, sword);
        player.getInventory().setItem(1, rod);
        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);

        return player.getInventory();
    }

    public static Inventory getCustomInv(Player player) {
        Inventory deserialized = ItemStackSerializer.deserializeInventory(string).toInventory(player);;
        return deserialized;
        }
    }
}
