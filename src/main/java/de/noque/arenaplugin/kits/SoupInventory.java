package de.noque.arenaplugin.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.potion.PotionEffectType.DAMAGE_RESISTANCE;

public class SoupInventory {

    public SoupInventory(Player player) {
        getDefaultInv(player);
    }

    public static Inventory getDefaultInv(Player player) {

        player.getInventory().clear();

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

        player.addPotionEffect(new PotionEffect(DAMAGE_RESISTANCE, 99999,2));

        //Give items
        for (int i = 0; i < 36; i++) {
            player.getInventory().setItem(i, soup);
        }

        player.getInventory().setItem(0, sword);

        player.getInventory().setItem(13, bm);
        player.getInventory().setItem(14, rm);
        player.getInventory().setItem(15, bowl);

        return player.getInventory();
    }
}
