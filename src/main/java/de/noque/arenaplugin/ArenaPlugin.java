package de.noque.arenaplugin;

import com.fren_gor.invManagementPlugin.api.itemStack.ItemStackSerializer;
import com.mongodb.client.MongoCollection;
import de.noque.arenaplugin.command.*;
import de.noque.arenaplugin.listener.*;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

@Getter
public final class ArenaPlugin extends JavaPlugin {

    //Plugin
    public static ArenaPlugin INSTANCE;
    public static Configuration config;

    //MongoDB
    private MongoManager mongoManager;
    @Getter
    private static MongoCollection<Document> statsCollection;
    @Getter
    private static MongoCollection<Document> editorCollection;

    //Blocks
    @Getter
    private static ArrayList<Block> newBlocks = new ArrayList<>();

    //Player State
    @Getter
    private static HashSet<UUID> lobby = new HashSet<>();
    @Getter
    private static HashMap<UUID, String> editor = new HashMap<>();
    @Getter
    private static HashMap<UUID, String> kit = new HashMap<>();


    @Override
    public void onEnable() {

        //DATABASE
        mongoManager = new MongoManager();
        mongoManager.connect();
        statsCollection = getMongoManager().getDatabase().getCollection("stats");
        editorCollection = getMongoManager().getDatabase().getCollection("editor");

        /*    Commands    */
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetspawnCommand());
        getCommand("damager").setExecutor(new DamagerCommand());
        getCommand("setdamager").setExecutor(new SetdamagerCommand());
        getCommand("clear").setExecutor(new ClearCommand());
        getCommand("fix").setExecutor(new FixCommand());
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("kit").setExecutor(new KitCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("reset").setExecutor(new ResetCommand());
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("resetstats").setExecutor(new ResetStatsCommand());
        getCommand("save").setExecutor(new SaveCommand());

        /*    Events    */
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new PreventionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new ServerListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        INSTANCE = this;
        config = this.getConfig();
        saveConfig();
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        mongoManager.disconnect();
    }

    /* Lobby */
    public static void addToLobby(Player player) {
        getLobby().add(player.getUniqueId());
    }
    public static void removeFromLobby(Player player) {
        getLobby().remove(player.getUniqueId());
    }

    /* Editor */
    public static void addToEditor(Player player, String string) {
        getEditor().put(player.getUniqueId(), string);
    }
    public static void removeFromEditor(Player player) {
        getEditor().remove(player.getUniqueId());
    }

    /* Kit */
    public static void addToKit(Player player, String string) {
        getKit().put(player.getUniqueId(), string);
    }
    public static void removeFromKit(Player player) {
        getKit().remove(player.getUniqueId());
    }


    public static void resetBlocks() {
        for (int i = 0; i < getNewBlocks().size(); i++) {

            if (getNewBlocks().get(i).getType() == Material.COBBLESTONE || getNewBlocks().get(i).getType() == Material.OBSIDIAN ||
                    getNewBlocks().get(i).getType() == Material.LAVA || getNewBlocks().get(i).getType() == Material.STATIONARY_LAVA ||
                    getNewBlocks().get(i).getType() == Material.WATER || getNewBlocks().get(i).getType() == Material.STATIONARY_WATER ||
                    getNewBlocks().get(i).getType() == Material.WOOD) {

                getNewBlocks().get(i).setType(Material.AIR);
            }
        }
        getNewBlocks().clear();
    }

    /* SERIALIZE INVENTORY */
    public static String serializeInv(Inventory inv) {
        return ItemStackSerializer.serializeInventory(inv);
    }

    /* DESERIALIZE INVENTORY */
    public static Inventory deserializeInv(Player player, String inv) {
        return ItemStackSerializer.deserializeInventory(inv).toInventory(player);
    }
}

