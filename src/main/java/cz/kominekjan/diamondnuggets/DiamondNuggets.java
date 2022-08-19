package cz.kominekjan.diamondnuggets;

import com.tchristofferson.configupdater.ConfigUpdater;
import cz.kominekjan.diamondnuggets.item.Nugget;
import cz.kominekjan.diamondnuggets.recipes.NuggetRecipe;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
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
        try {
            ConfigUpdater.update(this, "config.yml", new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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