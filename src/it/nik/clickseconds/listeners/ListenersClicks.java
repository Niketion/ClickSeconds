package it.nik.clickseconds.listeners;

import it.nik.clickseconds.commands.CommandClick;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Event of click
 */
public class ListenersClicks implements Listener {

    @EventHandler(priority= EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event){
        try {
            Player player = event.getPlayer();

            // Check if player is on clickMode
            if (!CommandClick.clickMode.containsKey(player.getName())) {
                return;
            }

            if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
                CommandClick.clickMode.put(player.getName(), CommandClick.clickMode.get(player.getName()) + 1);
            }
        } catch (Exception ignored) {}
    }
}
