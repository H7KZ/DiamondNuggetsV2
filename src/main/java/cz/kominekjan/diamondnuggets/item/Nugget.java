package cz.kominekjan.diamondnuggets.item;

import cz.kominekjan.diamondnuggets.ConVars;
import cz.kominekjan.diamondnuggets.Logs;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nugget {
    private static ItemStack item;

    public static ItemStack getItem() {
        return item;
    }

    public static Material setMaterial() {
        String nuggetStr = ConVars.Item.material;

        if (nuggetStr == null) {
            Logs.err("The item material was missing from the config!");
            Logs.err("Defaulting to Gold Nugget!");
            nuggetStr = "ECHO_SHARD";
        }

        Material nuggetMat = Material.matchMaterial(nuggetStr);

        if (nuggetMat == null) {
            Logs.err(nuggetStr + " is not a valid material!");
            Logs.err("Defaulting to Gold Nugget!");
            nuggetMat = Material.ECHO_SHARD;
        }

        // Air cannot be crafted
        if (nuggetMat.isAir()) {
            Logs.err("The item material cannot be air!");
            Logs.err("Defaulting to Gold Nugget!");
            nuggetMat = Material.ECHO_SHARD;
        }

        if (nuggetMat.getMaxStackSize() < 9) {
            Logs.err("The item material must have a max stack size of 9 or more!");
            Logs.err("Defaulting to Gold Nugget!");
            nuggetMat = Material.ECHO_SHARD;
        }

        return nuggetMat;
    }

    public static boolean init() {
        ItemStack nugget = new ItemStack(Objects.requireNonNull(setMaterial()));

        if (ConVars.Item.enchanted) {
            for (String enchantment : ConVars.Item.enchantments) {
                String[] enchantmentSplit = enchantment.split(":");

                if (enchantmentSplit.length != 2) {
                    Logs.err("Invalid enchantment: " + enchantment);
                    continue;
                }

                Enchantment ench = Enchantment.getByKey(NamespacedKey.minecraft(enchantmentSplit[0]));

                if (ench == null) {
                    Logs.err(enchantmentSplit[0] + " is not a valid enchantment!");
                    continue;
                }

                int level;

                try {
                    level = Integer.parseInt(enchantmentSplit[1]);
                } catch (NumberFormatException e) {
                    Logs.err(enchantmentSplit[1] + " is not a valid enchantment level!");
                    continue;
                }

                if (level < 1 || level > 32767) {
                    Logs.err(enchantmentSplit[1] + " is not a valid enchantment level!");
                    continue;
                }

                nugget.addUnsafeEnchantment(ench, level);
            }
        }

        ItemMeta meta = nugget.getItemMeta();

        String itemName = ConVars.Item.name;

        assert meta != null;
        assert itemName != null;
        if (!checkName(itemName)) return true;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));

        meta.setUnbreakable(true); // unbreakable flag prevents cheating with enchants

        if (ConVars.Item.flagged) {
            for (String itemFlag : ConVars.Item.flags) {
                try {
                    ItemFlag itemFlagEnum = ItemFlag.valueOf(itemFlag);

                    meta.addItemFlags(itemFlagEnum);
                } catch (IllegalArgumentException e) {
                    Logs.err(itemFlag + " is not a valid item flag!");
                }
            }
        }

        if (ConVars.Item.lored) {
            List<String> lore = new ArrayList<>();

            for (String loreItem : ConVars.Item.lore) {
                if (!checkLore(loreItem)) continue;

                lore.add(ChatColor.translateAlternateColorCodes('&', loreItem));
            }

            meta.setLore(lore);
        }

        meta.setCustomModelData(ConVars.Item.customModelData);

        nugget.setItemMeta(meta);

        item = nugget;

        return false;
    }

    private static boolean checkName(String name) {
        if (name == null || name.isEmpty()) {
            Logs.err("Item name cannot be empty!");
            return false;
        }

        if (name.length() > 50) {
            Logs.err("Item name must be 50 characters or less but was " + name.length() + " characters!");
            return false;
        }

        return true;
    }

    private static boolean checkLore(String lore) {
        if (lore == null || lore.isEmpty()) {
            Logs.err("Item lore cannot be empty!");
            return false;
        }

        if (lore.length() > 50) {
            Logs.err("Item lore must be 50 characters or less but was " + lore.length() + " characters!");
            return false;
        }

        return true;
    }
}
