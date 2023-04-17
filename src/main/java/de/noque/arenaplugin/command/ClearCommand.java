package de.noque.arenaplugin.command;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.gui.SpawnGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        /* COMMAND */
        if (!ArenaPlugin.getKit().get(uuid).equals("Damager")) {
            ArenaPlugin.addToLobby(player);
            ArenaPlugin.removeFromEditor(player);
            ArenaPlugin.removeFromKit(player);
            new SpawnGUI(player);
            return true;
        }

        /* ERROR HANDLING */
        if (ArenaPlugin.getKit().get(uuid).equals("Damager")) {
            player.sendMessage(ChatColor.RED + "You can't clear in the damager area.");
            return false;
        }
        if (ArenaPlugin.getLobby().contains(uuid)) {
            player.sendMessage(ChatColor.RED + "You can't clear if u have no kit selected.");
            return false;
        }

    return false;

    }
}
