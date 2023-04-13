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

    public static String serializeInv(Inventory inv) {
        String serialized = ItemStackSerializer.serializeInventory(inv);
        return serialized;
    }

    public static Inventory deserializeInv(Player player, String string) {
        Inventory deserialized = ItemStackSerializer.deserializeInventory(string).toInventory(player);;
        return deserialized;
    }

    /* LOAD STATS */
    public static void load(Player player, Inventory inv) {
        if (ArenaPlugin.getEditorCollection().countDocuments(Filters.eq("UUID", player.getUniqueId())) == 0) {
            ArenaPlugin.getEditorCollection().insertOne(new Document("UUID", player.getUniqueId())
                    .append("Rod", serializeInv(RodInventory.getInv(player)))
                    .append("Soup", serializeInv(SoupInventory.getInv(player)))
                    .append("BuildUHC", serializeInv(BuildUHCInventory.getInv(player))));
        }
    }

    /* RESET KIT */
    public static void reset(Player player) {
        ArenaPlugin.getEditorCollection().deleteOne(Filters.eq("UUID", player.getUniqueId()));
    }

    /* UPDATE KIT */
    public static void update(Player player, String kit) {
        ArenaPlugin.getEditorCollection().updateOne(Filters.eq("UUID", player.getUniqueId()),
                new Document("$inc", new Document(kit, serializeInv(player.getInventory()))));
    }

    /* GETTER */
    public static int getData(Player player, String kit) {
        Document found = ArenaPlugin.getEditorCollection().find(Filters.eq("UUID", player.getUniqueId())).first();
        return (int) found.get(kit);
    }
}
