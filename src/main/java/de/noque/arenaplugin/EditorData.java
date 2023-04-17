package de.noque.arenaplugin;

import com.mongodb.client.model.Filters;
import de.noque.arenaplugin.kits.BuildUHCInventory;
import de.noque.arenaplugin.kits.RodInventory;
import de.noque.arenaplugin.kits.SoupInventory;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EditorData {

    private static final Inventory rodInv = RodInventory.getInv();
    private static final Inventory soupInv = SoupInventory.getInv();
    private static final Inventory buildInv = BuildUHCInventory.getInv();

    /* LOAD STATS */
    public static void load(Player player) {
        if (ArenaPlugin.getEditorCollection().countDocuments(Filters.eq("UUID", player.getUniqueId())) == 0) {
            ArenaPlugin.getEditorCollection().insertOne(new Document("UUID", player.getUniqueId())
                    .append("Rod", ArenaPlugin.serializeInv(rodInv))
                    .append("Soup", ArenaPlugin.serializeInv(soupInv))
                    .append("BuildUHC", ArenaPlugin.serializeInv(buildInv)));
            System.out.println(ChatColor.BLUE + "EDITOR Document created for: " + player.getName());
        }
    }

    /* UPDATE KIT */
    public static void update(Player player, String kit) {
        ArenaPlugin.getEditorCollection().updateOne(Filters.eq("UUID", player.getUniqueId()),
                new Document("$inc", new Document(kit, ArenaPlugin.serializeInv(player.getInventory()))));
    }

    /* GET KIT */
    public static Inventory getInventory(Player player, String kit) {
        Document found = ArenaPlugin.getEditorCollection().find(Filters.eq("UUID", player.getUniqueId())).first();
        String inv = (String) found.get(kit);
        return ArenaPlugin.deserializeInv(player, inv);
    }
}
