package de.noque.arenaplugin;

import com.mongodb.client.model.Filters;
import de.noque.arenaplugin.kits.BuildUHCInventory;
import de.noque.arenaplugin.kits.RodInventory;
import de.noque.arenaplugin.kits.SoupInventory;
import de.noque.arenaplugin.util.InventorySerialization;
import org.bson.Document;
import org.bson.types.Binary;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EditorData {

    private static final byte[] serializedRod = InventorySerialization.serializeInventory(RodInventory.getInv());
    private static final byte[] serializedSoup = InventorySerialization.serializeInventory(SoupInventory.getInv());
    private static final byte[] serializedBuild = InventorySerialization.serializeInventory(BuildUHCInventory.getInv());

    /* LOAD STATS */
    public static void load(Player player) {
        if (ArenaPlugin.getEditorCollection().countDocuments(Filters.eq("UUID", player.getUniqueId())) == 0) {
            ArenaPlugin.getEditorCollection().insertOne(new Document("UUID", player.getUniqueId())
                    .append("Rod", serializedRod)
                    .append("Soup", serializedSoup)
                    .append("BuildUHC", serializedBuild));
            System.out.println(ChatColor.BLUE + "EDITOR Document created for: " + player.getName());
        }
    }

    /* UPDATE KIT */
    public static void update(Player player, Inventory newInv, String kit) {
        byte[] inv = InventorySerialization.serializeInventory(newInv);
        assert inv != null;
        ArenaPlugin.getEditorCollection().updateOne(Filters.eq("UUID", player.getUniqueId()),
                new Document("$set", new Document(kit, inv)));
    }

    /* GET KIT */
    public static Inventory getInventory(Player player, String kit) {
        Document found = ArenaPlugin.getEditorCollection().find(Filters.eq("UUID", player.getUniqueId())).first();
        assert found != null;
        byte[] inv = found.get(kit, Binary.class).getData();
        return InventorySerialization.deserializeInventory(inv);
    }
}
