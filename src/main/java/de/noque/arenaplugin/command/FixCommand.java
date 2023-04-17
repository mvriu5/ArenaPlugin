package de.noque.arenaplugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FixCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] arhs) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        /* COMMAND */
        player.teleport(player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Fixed!");

        return false;

    }
}
