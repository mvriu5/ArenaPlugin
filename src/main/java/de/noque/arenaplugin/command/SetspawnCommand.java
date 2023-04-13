package de.noque.arenaplugin.command;

import de.noque.arenaplugin.ConfigManager;
import lombok.SneakyThrows;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetspawnCommand implements CommandExecutor {

    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) { return false; }

        Player player = (Player) sender;

        if (player.isOp()) {

            ConfigManager.setSpawn(player.getLocation());

            player.sendMessage(ChatColor.YELLOW + "You set the spawnpoint!");

        } else { player.sendMessage(ChatColor.RED + "You don't got permission to do that"); }

        return false;
    }
}
