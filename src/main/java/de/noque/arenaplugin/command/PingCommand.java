package de.noque.arenaplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length == 0) {
                int ping = ((CraftPlayer) player).getHandle().ping;
                player.sendMessage(ChatColor.GREEN + "Your ping: " + ping + "ms");

            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    int target_ping = ((CraftPlayer) target).getHandle().ping;
                    player.sendMessage(ChatColor.GREEN + target.getName() + "'s ping: " + target_ping + "ms");

                } else {
                    player.sendMessage(ChatColor.RED + "This Player is not online.");
                }
            }

        }

        return false;
    }
}
