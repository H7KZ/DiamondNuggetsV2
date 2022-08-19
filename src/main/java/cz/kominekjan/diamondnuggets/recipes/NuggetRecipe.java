package cz.kominekjan.diamondnuggets.recipes;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.*;

import static cz.kominekjan.diamondnuggets.DiamondNuggets.*;

public class NuggetRecipe {
    // Known bug: recipe book doesn't autofill custom items
    // ingredientCount assumed 1-9
    public static void addToDiamondRecipe(int ingredientCount) {
        Plugin().getServer().addRecipe(getToDiamondRecipe(ingredientCount));
    }

    public static void removeToDiamondRecipe() {
        Plugin().getServer().removeRecipe(ToDiamondKey());
    }

    public static Recipe getToDiamondRecipe(int ingredientCount) {
        // Shapeless recipe doesn't work with nbt :(
        ShapedRecipe toDiamondRecipe = new ShapedRecipe(ToDiamondKey(), new ItemStack(Material.DIAMOND, 1));
        toDiamondRecipe.shape(getToDiamondRecipeShape(ingredientCount));
        toDiamondRecipe.setIngredient('#', new RecipeChoice.ExactChoice(Nugget()));
        return toDiamondRecipe;
    }

    public static String[] getToDiamondRecipeShape(int ingredientCount) {
        return Shapes()[ingredientCount - 1];
    }

    // ingredientCount assumed 1-64
    public static void addToNuggetRecipe(int ingredientCount) {
        ItemStack nuggets = Nugget().clone();
        nuggets.setAmount(ingredientCount);
        ShapelessRecipe toNuggetRecipe = new ShapelessRecipe(ToNuggetKey(), nuggets);
        toNuggetRecipe.addIngredient(1, Material.DIAMOND);
        Plugin().getServer().addRecipe(toNuggetRecipe);
    }

    public static void removeToNuggetRecipe() {
        Plugin().getServer().removeRecipe(ToNuggetKey());
    }

    /**
     * @param inv The player's inventory
     * @return True if the player has a diamond or diamond nugget and should unlock recipes
     */
    public static boolean shouldUnlockRecipes(Inventory inv) {
        return inv.contains(Material.DIAMOND) || inv.containsAtLeast(Nugget(), 1);
    }

    /**
     * Unlocks the diamond and diamond nugget recipes.
     *
     * @param player The player to unlock recipes for
     */
    public static void unlockRecipes(HumanEntity player) {
        player.discoverRecipe(ToNuggetKey());
        player.discoverRecipe(ToDiamondKey());
    }
}
