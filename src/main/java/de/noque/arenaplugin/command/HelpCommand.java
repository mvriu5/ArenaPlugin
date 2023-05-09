package de.noque.arenaplugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        /* COMMAND */
        StringBuilder spaces = new StringBuilder(); //create spaces for text
        for (int i = 0; i < 33; i++) {
            spaces.append(" ");
        }
        player.sendMessage(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + spaces + ChatColor.RESET + ChatColor.GOLD + " HELP " + ChatColor.GOLD + ChatColor.STRIKETHROUGH + spaces);
        player.sendMessage(ChatColor.YELLOW + "/spawn" + ChatColor.GRAY + " - Teleports you to the spawn");
        player.sendMessage(ChatColor.YELLOW + "/damager" + ChatColor.GRAY + " - Teleports you to the damager");
        player.sendMessage(ChatColor.YELLOW + "/fix" + ChatColor.GRAY + " - Get your location fixxed");
        player.sendMessage(ChatColor.YELLOW + "/clear" + ChatColor.GRAY + " - Clears your kit");
        player.sendMessage(ChatColor.YELLOW + "/ping <player>" + ChatColor.GRAY + " - Get the ping of a player");
        player.sendMessage(ChatColor.YELLOW + "/kit <player>" + ChatColor.GRAY + " - Get the current kit of a player");
        player.sendMessage(ChatColor.YELLOW + "/stats <player>" + ChatColor.GRAY + " - Get the stats of a player");

        return false;
    }
}
