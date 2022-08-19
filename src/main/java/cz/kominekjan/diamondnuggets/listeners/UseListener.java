package cz.kominekjan.diamondnuggets.listeners;

import cz.kominekjan.diamondnuggets.DiamondNuggets;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static cz.kominekjan.diamondnuggets.DiamondNuggets.Nugget;

public class UseListener implements Listener {

    public UseListener(DiamondNuggets plugin) {
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (item != null && item.isSimilar(Nugget())) {
            e.setUseItemInHand(Event.Result.DENY);
        }
    }

}
