package cz.kominekjan.diamondnuggets;

import java.util.List;

import static cz.kominekjan.diamondnuggets.DiamondNuggets.Config;

public class ConVars {
    public static int Nuggets_to_diamond;
    public static boolean Unlock_recipe_on_join;
    public static boolean Prevent_nugget_rename;
    public static String Disable_rename_message;
    public static boolean Prevent_nugget_crafting;
    public static boolean Prevent_nugget_placement;

    public static void reload() {
        Item.material = Config().getString("item.material", "GOLD_NUGGET");
        Item.customModelData = Config().getInt("item.customModelData", 5648554);
        Item.name = Config().getString("item.name", "&rDiamond nugget");
        Item.lored = Config().getBoolean("item.lored", true);
        Item.lore = Config().getStringList("item.lore");
        Item.enchanted = Config().getBoolean("item.enchanted", false);
        Item.enchantments = Config().getStringList("item.enchantments");
        Item.flagged = Config().getBoolean("item.flagged", true);
        Item.flags = Config().getStringList("item.flags");

        Nuggets_to_diamond = Config().getInt("nuggets_to_diamond", 9);

        Unlock_recipe_on_join = Config().getBoolean("unlock_recipe_on_join", false);

        Prevent_nugget_rename = Config().getBoolean("prevent_nugget_rename", true);

        Disable_rename_message = Config().getString("rename_disabled_message", "&cYou can't rename this item!");

        Prevent_nugget_crafting = Config().getBoolean("prevent_nugget_crafting", true);

        Prevent_nugget_placement = Config().getBoolean("prevent_nugget_placement", false);
    }

    public static class Item {
        public static String material;
        public static int customModelData;
        public static String name;
        public static boolean lored;
        public static List<String> lore;
        public static boolean enchanted;
        public static List<String> enchantments;
        public static boolean flagged;
        public static List<String> flags;
    }
}
