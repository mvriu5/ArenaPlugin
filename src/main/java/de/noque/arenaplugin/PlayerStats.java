package de.noque.arenaplugin;

import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerStats {

    /* LOAD STATS */
    public static void load(Player player) {
        if (ArenaPlugin.getStatsCollection().countDocuments(Filters.eq("UUID", player.getUniqueId())) == 0) {
            ArenaPlugin.getStatsCollection().insertOne(new Document("UUID", player.getUniqueId()).append("kills", 0).append("deaths", 0));
            System.out.println(ChatColor.BLUE + "Document created for: "+ player.getName());
        }
    }

    /* RESET STATS */
    public static void reset(OfflinePlayer player) {
        ArenaPlugin.getStatsCollection().updateOne(Filters.eq("UUID", player.getUniqueId()), new Document("$set", new Document("kills", 0).append("deaths", 0)));
        System.out.println(ChatColor.BLUE + "Resetted the stats of " + player.getName());
    }


    /* GETTER */
    public static int getKills(OfflinePlayer player) {
        Document found = ArenaPlugin.getStatsCollection().find(Filters.eq("UUID", player.getUniqueId())).first();
        return (int) found.get("kills");
    }
    public static int getDeaths(OfflinePlayer player) {
        Document found = ArenaPlugin.getStatsCollection().find(Filters.eq("UUID", player.getUniqueId())).first();
        return (int) found.get("deaths");
    }
    public static double getKD(OfflinePlayer player) {
        return (double) getKills(player) / getDeaths(player);
    }


    /* WORK WITH VALUES */
    public static void addKill(Player player) {
        ArenaPlugin.getStatsCollection().updateOne(Filters.eq("UUID", player.getUniqueId()), new Document("$inc", new Document("kills", 1)));
    }
    public static void addDeath(Player player) {
        ArenaPlugin.getStatsCollection().updateOne(Filters.eq("UUID", player.getUniqueId()), new Document("$inc", new Document("deaths", 1)));
    }
}
