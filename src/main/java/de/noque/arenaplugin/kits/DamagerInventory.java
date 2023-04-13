package de.noque.arenaplugin.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DamagerInventory {

    public DamagerInventory(Player player) {

        player.getInventory().clear();

        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);

        ItemStack bm = new ItemStack(Material.BROWN_MUSHROOM);
        bm.setAmount(64);

        ItemStack rm = new ItemStack(Material.RED_MUSHROOM);
        rm.setAmount(64);

        ItemStack bowl = new ItemStack(Material.BOWL);
        bowl.setAmount(64);


        //Give items
        for (int i = 0; i < 36; i++) {
            player.getInventory().setItem(i, soup);
        }


        player.getInventory().setItem(13, bm);
        player.getInventory().setItem(14, rm);
        player.getInventory().setItem(15, bowl);
    }
}
