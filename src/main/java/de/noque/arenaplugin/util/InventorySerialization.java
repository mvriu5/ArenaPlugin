package de.noque.arenaplugin.util;

import net.minecraft.util.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class InventorySerialization {

    public static byte[] serializeInventory(Inventory inventory) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // Write the size of the inventory
            dataOutput.writeInt(inventory.getSize());

            // Write the contents of the inventory
            for (ItemStack item : inventory.getContents()) {
                dataOutput.writeObject(item);
            }

            // Serialize the inventory to a byte array
            dataOutput.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            Bukkit.getLogger().severe("Could not serialize inventory: " + e.getMessage());
            return null;
        }
    }

    public static Inventory deserializeInventory(byte[] serializedInventory) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(serializedInventory);
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            // Read the size of the inventory
            int size = dataInput.readInt();

            // Create a new inventory of the correct size
            Inventory inventory = Bukkit.getServer().createInventory(null, size);

            // Read the contents of the inventory
            for (int i = 0; i < size; i++) {
                ItemStack item = (ItemStack) dataInput.readObject();
                inventory.setItem(i, item);
            }

            // Close the data input stream
            dataInput.close();
            return inventory;
        } catch (IOException | ClassNotFoundException e) {
            Bukkit.getLogger().severe("Could not deserialize inventory: " + e.getMessage());
            return null;
        }
    }
}
