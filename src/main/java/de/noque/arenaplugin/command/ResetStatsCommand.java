package de.noque.arenaplugin.command;

import de.noque.arenaplugin.StatsData;
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

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        /* COMMAND */
        if (player.isOp() && args.length == 1 && target.hasPlayedBefore()) {
            StatsData.reset(target);
            player.sendMessage(ChatColor.YELLOW + "You resetted the statistics of " + target.getName());
            return false;
        }

        /* ERROR HANDLING */
        if (!player.isOp()) {
            player.sendMessage(ChatColor.RED + "You don't got permission to do that");
            return false;
        }
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid usage! Use: /reset <player>");
            return false;
        }
        if (target == null) {
            player.sendMessage(ChatColor.RED + "The requested player doesn't exist.");
            return false;
        }
        return false;
    }
}
