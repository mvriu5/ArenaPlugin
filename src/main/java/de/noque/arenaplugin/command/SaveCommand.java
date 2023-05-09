package de.noque.arenaplugin.command;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.EditorData;
import de.noque.arenaplugin.gui.SpawnGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SaveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        /* COMMAND */
        if (ArenaPlugin.getEditor().containsKey(uuid)) {
            EditorData.update(player, player.getInventory(), ArenaPlugin.getEditor().get(uuid));
            ArenaPlugin.removeFromEditor(player);
            new SpawnGUI(player);
            player.sendMessage(ChatColor.GREEN + "Successfully updated your " + ArenaPlugin.getEditor().get(player.getUniqueId()) + " kit.");
            return false;
        }

        /* ERROR HANDLING */
        player.sendMessage(ChatColor.RED + "You are not in the editor right now.");
        return false;
    }
}
