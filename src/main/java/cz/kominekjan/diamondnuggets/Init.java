package cz.kominekjan.diamondnuggets;

import cz.kominekjan.diamondnuggets.command.ReloadCommand;
import cz.kominekjan.diamondnuggets.listeners.*;
import cz.kominekjan.diamondnuggets.recipes.NuggetRecipe;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.PluginManager;

import java.util.Objects;

import static cz.kominekjan.diamondnuggets.DiamondNuggets.Plugin;

public class Init {
    public static void command() {
        Objects.requireNonNull(Plugin().getCommand("diamondnuggetsreload")).setExecutor(new ReloadCommand());
    }

    public static void recipes() {
        // Don't add recipe if amount of nuggets is invalid
        int ingredientCount = ConVars.Nuggets_to_diamond;
        if (1 <= ingredientCount && ingredientCount <= 9) {

            NuggetRecipe.addToNuggetRecipe(ingredientCount);
            NuggetRecipe.addToDiamondRecipe(ingredientCount);

            // In case plugin is reloaded while server is running, give recipes to all online players
            for (Player p : Plugin().getServer().getOnlinePlayers()) {
                if (ConVars.Unlock_recipe_on_join || NuggetRecipe.shouldUnlockRecipes(p.getInventory())) {
                    NuggetRecipe.unlockRecipes(p);
                }
            }

        } else {
            Logs.err("Amount of nuggets to craft a diamond must be between 1-9 but was " + ingredientCount + "!");
        }
    }

    public static void events() {
        // Prevent renaming even if recipes are temporarily invalid
        PluginManager man = Plugin().getServer().getPluginManager();
        man.registerEvents(new UseListener(Plugin()), Plugin());
        man.registerEvents(new UnlockListener(Plugin()), Plugin());

        if (ConVars.Prevent_nugget_crafting) {
            man.registerEvents(new CraftListener(Plugin()), Plugin());
            man.registerEvents(new InventoryDenyListener(Plugin(), InventoryType.STONECUTTER, 0), Plugin());
            man.registerEvents(new InventoryDenyListener(Plugin(), InventoryType.CARTOGRAPHY, 0, 1), Plugin());
            man.registerEvents(new InventoryDenyListener(Plugin(), InventoryType.LOOM, 0, 1, 2), Plugin());
            man.registerEvents(new InventoryDenyListener(Plugin(), InventoryType.BREWING, 3), Plugin());
            man.registerEvents(new InventoryDenyListener(Plugin(), InventoryType.MERCHANT, 0, 1), Plugin());
            man.registerEvents(new BrewListener(Plugin()), Plugin());
        }
        if (ConVars.Prevent_nugget_rename) {
            man.registerEvents(new InventoryDenyListener(Plugin(), InventoryType.ANVIL, true, 0, 1), Plugin());
        }
        man.registerEvents(new InventoryDenyListener(Plugin(), InventoryType.GRINDSTONE, 0, 1), Plugin());
    }
}
