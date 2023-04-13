package de.noque.arenaplugin;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

@Getter
public class MongoManager {

    private MongoClient client;
    private MongoDatabase database;

    public void connect() {
        client = new MongoClient("202.61.243.216", 27017);
        database = client.getDatabase("pvpserver");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Connected to MongoDB!");
    }

    public void disconnect() {
        if (client != null) {
            client.close();
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Disconnected from MongoDB!");
    }
}
