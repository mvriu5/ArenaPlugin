package de.noque.arenaplugin.command;

import de.noque.arenaplugin.ArenaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class KitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);


        /* COMMAND */
        if (args.length == 1 && Bukkit.getPlayer(args[0]) != null && ArenaPlugin.getKit().containsKey(target.getUniqueId())) {
            player.sendMessage(ChatColor.GREEN + target.getName() + ChatColor.GRAY + " is playing with " + ChatColor.GOLD + ArenaPlugin.getKit().get(target.getUniqueId()) + " Kit" + ChatColor.GRAY + ".");
            return true;
        }

        /* ERROR HANDLING*/
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid usage! Use /kit <player>.");
            return false;
        }
        if (target == null) {
            player.sendMessage(ChatColor.RED + "This Player is not online.");
            return false;
        }
        if (!(ArenaPlugin.getKit().containsKey(target.getUniqueId()))) {
            player.sendMessage(ChatColor.RED + "This Player has no kit selected.");
            return false;
        }

    return false;

    }
}
