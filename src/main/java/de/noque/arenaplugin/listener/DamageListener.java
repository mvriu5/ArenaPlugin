package de.noque.arenaplugin.listener;

import de.noque.arenaplugin.ArenaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        Player player = (Player) e.getEntity();

        //FallDamage, LobbyDamage canceln
        if (ArenaPlugin.getLobby().contains(player.getUniqueId()) || player.getLocation().getY() >= 29 || e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {

        Player player = (Player) e.getEntity();

        if(e.getDamager() instanceof  Player){
            Player damager = (Player) e.getDamager();

            //Wenn einer der Spieler in Lobby ist + im Damager canceln
            if (ArenaPlugin.getLobby().contains(player.getUniqueId()) || ArenaPlugin.getLobby().contains(damager.getUniqueId())
                    || player.getLocation().getY() >= 29 ||damager.getLocation().getY() >= 29 || ArenaPlugin.getKit().get(player.getUniqueId()).equals("Damager")) {
                e.setCancelled(true);
            }
        }
    }
}
