package de.noque.arenaplugin.command;

import de.noque.arenaplugin.StatsData;
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

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        /* YOUR STATS */
        if (args.length == 0) {
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Your Statistics:");
            player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.STRIKETHROUGH + "                           ");
            player.sendMessage(ChatColor.YELLOW + "Kill: " + StatsData.getKills(player));
            player.sendMessage(ChatColor.YELLOW + "Death: " + StatsData.getDeaths(player));
            player.sendMessage(ChatColor.YELLOW + "K/D: " + StatsData.getKD(player));
            player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.STRIKETHROUGH + "                           ");
            return true;
        }

        /* OTHER PLAYERS STATS */
        if (args.length == 1) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

            if (target.hasPlayedBefore()) {
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + target.getName() + "'s Statistics:");
                player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.STRIKETHROUGH + "                                  ");
                player.sendMessage(ChatColor.YELLOW + "Kill: " + StatsData.getKills(target));
                player.sendMessage(ChatColor.YELLOW + "Death: " + StatsData.getDeaths(target));
                player.sendMessage(ChatColor.YELLOW + "K/D: " + StatsData.getKD(target));
                player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.STRIKETHROUGH + "                                  ");
                return true;

            }
            /* ERROR HANDLING */
            if (!target.hasPlayedBefore()) {
                player.sendMessage(ChatColor.RED + "The requested player doesn't exist.");
                return false;
            }
        }

        /* ERROR HANDLING */
        if (args.length > 1) {
            player.sendMessage(ChatColor.RED + "Invalid usage! Use: /stats <player>");
            return false;
        }

        return false;

    }
}