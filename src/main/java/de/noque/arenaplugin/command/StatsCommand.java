package de.noque.arenaplugin.command;

import de.noque.arenaplugin.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            /* YOUR STATS */
            if (args.length == 0) {
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Your Statistics:");
                player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.STRIKETHROUGH + "                           ");
                player.sendMessage(ChatColor.YELLOW + "Kill: " + PlayerStats.getKills(player));
                player.sendMessage(ChatColor.YELLOW + "Death: " + PlayerStats.getDeaths(player));
                player.sendMessage(ChatColor.YELLOW + "K/D: " + PlayerStats.getKD(player));
                player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.STRIKETHROUGH + "                           ");

            /* OTHER PLAYERS STATS */
            } else if (args.length == 1) {

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                if (target.hasPlayedBefore()) {
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + target.getName() + "'s Statistics:");
                    player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.STRIKETHROUGH + "                                  ");
                    player.sendMessage(ChatColor.YELLOW + "Kill: " + PlayerStats.getKills(target));
                    player.sendMessage(ChatColor.YELLOW + "Death: " + PlayerStats.getDeaths(target));
                    player.sendMessage(ChatColor.YELLOW + "K/D: " + PlayerStats.getKD(target));
                    player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.STRIKETHROUGH + "                                  ");

                } else { player.sendMessage(ChatColor.RED + "The requested player doesn't exist."); }
            } else { player.sendMessage(ChatColor.RED + "Invalid usage! Use: /stats <player>"); }
        }
        return false;
    }
}