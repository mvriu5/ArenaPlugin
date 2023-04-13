package de.noque.arenaplugin.command;

import de.noque.arenaplugin.ArenaPlugin;
import de.noque.arenaplugin.EditorData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (ArenaPlugin.getEditor().containsKey(player.getUniqueId())) {
                String kit = ArenaPlugin.getEditor().get(player.getUniqueId());

                EditorData.update(player, kit);
                ArenaPlugin.removeFromEditor(player);
                player.sendMessage(ChatColor.GREEN + "Successfully updated your " + ArenaPlugin.getEditor().get(player.getUniqueId()) + " kit.");

            } else { player.sendMessage(ChatColor.RED + "You are not in the editor right now."); }
        }
        return false;
    }
}
