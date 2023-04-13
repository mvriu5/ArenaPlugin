package de.noque.arenaplugin.command;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.gui.SpawnGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (ArenaPlugin.getLobby().contains(player.getUniqueId())) {
                player.sendMessage(ChatColor.RED + "You can't clear if u have no kit selected.");

            } else {
                if (!(ArenaPlugin.getKit().get(player.getUniqueId()).equals("Damager"))) {
                    new SpawnGUI(player);
                    ArenaPlugin.addToLobby(player);
                    ArenaPlugin.removeFromEditor(player);
                    ArenaPlugin.removeFromKit(player);

                } else { player.sendMessage(ChatColor.RED + "You can't clear in the damager area."); }
            }
        }
        return false;
    }
}
