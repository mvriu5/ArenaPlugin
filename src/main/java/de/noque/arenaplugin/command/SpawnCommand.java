package de.noque.arenaplugin.command;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.ConfigManager;
import de.noque.arenaplugin.gui.SpawnGUI;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        ArenaPlugin.addToLobby(player);
        ArenaPlugin.removeFromKit(player);
        ArenaPlugin.removeFromEditor(player);
        new SpawnGUI(player);
        player.playSound(player.getLocation(), Sound.CLICK, 1.0F, 0.5F);

        try {
            player.teleport(ConfigManager.getSpawn());
        } catch (NullPointerException ex) {
            player.sendMessage(ChatColor.RED + "Spawn isn't set yet.");
        }
        return false;
    }
}
