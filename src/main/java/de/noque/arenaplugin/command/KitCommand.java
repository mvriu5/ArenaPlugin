package de.noque.arenaplugin.command;

import de.noque.arenaplugin.ArenaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    if (ArenaPlugin.getKit().containsKey(target.getUniqueId())) {

                        player.sendMessage(ChatColor.GREEN + target.getName() + ChatColor.GRAY + " is playing with " +
                                ChatColor.GOLD + ArenaPlugin.getKit().get(target.getUniqueId()) + " Kit" + ChatColor.GRAY + ".");

                    } else { player.sendMessage(ChatColor.RED + "This Player has no kit selected."); }
                } else { player.sendMessage(ChatColor.RED + "This Player is not online."); }
            } else { player.sendMessage(ChatColor.RED + "Invalid usage! Use /kit <player>."); }
        }
        return false;
    }
}
