package cz.kominekjan.diamondnuggets.listeners;

import cz.kominekjan.diamondnuggets.DiamondNuggets;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewingStandFuelEvent;

import static cz.kominekjan.diamondnuggets.DiamondNuggets.Nugget;

public class BrewListener implements Listener {

    public BrewListener(DiamondNuggets plugin) {
    }

    @EventHandler
    public void onBrewFuel(BrewingStandFuelEvent e) {
        if (e.getFuel().isSimilar(Nugget())) {
            e.setCancelled(true);
        }
    }

}
