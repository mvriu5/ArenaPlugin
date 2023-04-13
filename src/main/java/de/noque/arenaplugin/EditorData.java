package de.noque.arenaplugin;

import com.fren_gor.invManagementPlugin.api.itemStack.ItemStackSerializer;
import com.mongodb.client.model.Filters;
import de.noque.arenaplugin.kits.BuildUHCInventory;
import de.noque.arenaplugin.kits.RodInventory;
import de.noque.arenaplugin.kits.SoupInventory;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EditorData {


    /* SERIALIZE */
    public static String serializeInv(Inventory inv) {
        return ItemStackSerializer.serializeInventory(inv);
    }

    /* DESERIALIZE */
    public static Inventory deserializeInv(Player player, String kit) {
        return ItemStackSerializer.deserializeInventory(kit).toInventory(player);
    }

    /* LOAD STATS */
    public static void load(Player player) {
        if (ArenaPlugin.getEditorCollection().countDocuments(Filters.eq("UUID", player.getUniqueId())) == 0) {
            ArenaPlugin.getEditorCollection().insertOne(new Document("UUID", player.getUniqueId())
                    .append("Rod", serializeInv(RodInventory.getDefaultInv(player)))
                    .append("Soup", serializeInv(SoupInventory.getDefaultInv(player)))
                    .append("BuildUHC", serializeInv(BuildUHCInventory.getDefaultInv(player))));
        }
    }

    /* UPDATE KIT */
    public static void update(Player player, String kit) {
        ArenaPlugin.getEditorCollection().updateOne(Filters.eq("UUID", player.getUniqueId()),
                new Document("$inc", new Document(kit, serializeInv(player.getInventory()))));
    }

    /* GETTER */
    public static Inventory getData(Player player, String kit) {
        Document found = ArenaPlugin.getEditorCollection().find(Filters.eq("UUID", player.getUniqueId())).first();
        String inv = (String) found.get(kit);
        return deserializeInv(player, inv);
    }
}
