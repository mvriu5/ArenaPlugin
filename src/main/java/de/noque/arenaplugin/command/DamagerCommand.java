package de.noque.arenaplugin.command;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.ConfigManager;
import de.noque.arenaplugin.gui.SpawnGUI;
import de.noque.arenaplugin.kits.DamagerInventory;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DamagerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            ArenaPlugin.removeFromLobby(player);
            ArenaPlugin.removeFromEditor(player);
            ArenaPlugin.removeFromKit(player);
            ArenaPlugin.addToKit(player, "Damager");

            new SpawnGUI(player);
            new DamagerInventory(player);

            player.playSound(player.getLocation(), Sound.CLICK, 1.0F, 0.5F);
            player.setGameMode(GameMode.SURVIVAL);

            try {
                player.teleport(ConfigManager.getDamager());
            } catch (NullPointerException ex) {
                player.sendMessage(ChatColor.RED + "Damager isn't set yet.");
            }
        }
        return false;
    }
}
