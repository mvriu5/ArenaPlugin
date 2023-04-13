package de.noque.arenaplugin.command;

import de.noque.arenaplugin.ArenaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.isOp()) {

                for (int i = 0; i < ArenaPlugin.getNewBlocks().size(); i++) {

                    if (ArenaPlugin.getNewBlocks().get(i).getType() == Material.COBBLESTONE || ArenaPlugin.getNewBlocks().get(i).getType() == Material.OBSIDIAN ||
                        ArenaPlugin.getNewBlocks().get(i).getType() == Material.LAVA || ArenaPlugin.getNewBlocks().get(i).getType() == Material.STATIONARY_LAVA ||
                        ArenaPlugin.getNewBlocks().get(i).getType() == Material.WATER || ArenaPlugin.getNewBlocks().get(i).getType() == Material.STATIONARY_WATER ||
                        ArenaPlugin.getNewBlocks().get(i).getType() == Material.WOOD) {

                        ArenaPlugin.getNewBlocks().get(i).setType(Material.AIR);
                    }
                }
                ArenaPlugin.getNewBlocks().clear();

                player.sendMessage(ChatColor.YELLOW + "You resetted the map.");
            }
        }

        return false;
    }
}
