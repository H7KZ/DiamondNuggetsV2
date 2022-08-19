package cz.kominekjan.diamondnuggets.command;

import cz.kominekjan.diamondnuggets.ConVars;
import cz.kominekjan.diamondnuggets.DiamondNuggets;
import cz.kominekjan.diamondnuggets.Init;
import cz.kominekjan.diamondnuggets.item.Nugget;
import cz.kominekjan.diamondnuggets.recipes.NuggetRecipe;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.sql.Time;

import static cz.kominekjan.diamondnuggets.DiamondNuggets.Plugin;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (!command.getName().equalsIgnoreCase("diamondnuggetsreload")) {
            return true;
        }

        if (!sender.hasPermission("diamondnuggets.reload")) {
            sender.sendMessage("You do not have permission to use this command!");
            return true;
        }

        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + "[Diamond Nuggets]" + ChatColor.YELLOW + " Reloading plugin/config...");
        } else {
            sender.sendMessage("[Diamond Nuggets] Reloading plugin/config...");
        }

        Time time = new Time(System.currentTimeMillis());

        reloadConfig();

        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + "[Diamond Nuggets]" + ChatColor.GREEN + " Config reloaded in " + (System.currentTimeMillis() - time.getTime()) + "ms");
        } else {
            sender.sendMessage("[Diamond Nuggets] Config reloaded in " + (System.currentTimeMillis() - time.getTime()) + "ms");
        }

        return true;
    }

    private void reloadConfig() {
        NuggetRecipe.removeToDiamondRecipe();
        NuggetRecipe.removeToNuggetRecipe();

        HandlerList.unregisterAll(Plugin());

        Plugin().saveDefaultConfig();
        Plugin().getConfig().options().copyDefaults(true);
        Plugin().reloadConfig();

        DiamondNuggets.config = Plugin().getConfig();

        ConVars.reload();

        if (Nugget.init()) {
            Plugin().getServer().getPluginManager().disablePlugin(Plugin());
        }

        Init.command();

        Init.recipes();

        Init.events();
    }
}
