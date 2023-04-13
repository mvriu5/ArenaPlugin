package de.noque.arenaplugin.command;

import de.noque.arenaplugin.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetStatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp()) {

                if (args.length == 1) {

                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                    if (target.hasPlayedBefore()) {
                        PlayerStats.reset(target);
                        player.sendMessage(ChatColor.YELLOW + "You resetted the statistics of " + target.getName());

                    } else { player.sendMessage(ChatColor.RED + "The requested player doesn't exist."); }
                } else { player.sendMessage(ChatColor.RED + "Invalid usage! Use: /reset <player>"); }
            } else { player.sendMessage(ChatColor.RED + "You don't got permission to do that"); }
        }
        return false;
    }
}
