package cz.kominekjan.diamondnuggets.listeners;

import cz.kominekjan.diamondnuggets.ConVars;
import cz.kominekjan.diamondnuggets.DiamondNuggets;
import cz.kominekjan.diamondnuggets.recipes.NuggetRecipe;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class UnlockListener implements Listener {

    public UnlockListener(DiamondNuggets plugin) {
    }

    // Events cover all detectable cases where an item is added to a player's inventory
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (ConVars.Unlock_recipe_on_join || NuggetRecipe.shouldUnlockRecipes(e.getPlayer().getInventory())) {
            NuggetRecipe.unlockRecipes(e.getPlayer());
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player p) {
            onInventoryChange(p);
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent e) {
        onInventoryChange(e.getWhoClicked());
    }

    private void onInventoryChange(HumanEntity player) {
        if (NuggetRecipe.shouldUnlockRecipes(player.getInventory())) {
            NuggetRecipe.unlockRecipes(player);
        }
    }

}
