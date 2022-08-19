package cz.kominekjan.diamondnuggets;

import cz.kominekjan.diamondnuggets.item.Nugget;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class DiamondNuggets extends JavaPlugin {
    private static final String[][] SHAPES = {
            {"#"},
            {"##"},
            {"# ", "##"},
            {"##", "##"},
            {" # ", "###", " # "},
            {"###", "###"},
            {" # ", "###", "###"},
            {"###", "# #", "###"},
            {"###", "###", "###"}
    };
    public static FileConfiguration config;
    public static NamespacedKey toNuggetKey;
    public static NamespacedKey toDiamondKey;
    private static DiamondNuggets plugin;
    private static Logger logger;

    public static DiamondNuggets Plugin() {
        return plugin;
    }

    public static FileConfiguration Config() {
        return config;
    }

    public static Logger Logger() {
        return logger;
    }

    public static String[][] Shapes() {
        return SHAPES;
    }

    public static NamespacedKey ToNuggetKey() {
        return toNuggetKey;
    }

    public static NamespacedKey ToDiamondKey() {
        return toDiamondKey;
    }

    public static ItemStack Nugget() {
        return Nugget.getItem();
    }

    @Override
    public void onEnable() {
        plugin = this;

        logger = getLogger();

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        reloadConfig();

        config = getConfig();

        toNuggetKey = new NamespacedKey(this, "nugget");
        toDiamondKey = new NamespacedKey(this, "diamond");

        ConVars.reload();

        if (Nugget.init()) {
            return;
        }

        Init.command();

        Init.recipes();

        Init.events();
    }

    @Override
    public void onDisable() {
    }
}