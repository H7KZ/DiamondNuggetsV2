# DiamondNuggetsV2

## Fork from https://github.com/Tisawesomeness/DiamondNuggets

## Config:
```yaml
# This plugin is meant for fairly vanilla servers.
# If you want more robust recipe configuration or protection against glitches/exploits
# from other plugins, consider using a dedicated custom recipe or custom item plugin.

item:
  # The nugget material used to represent diamond nuggets, cannot be air or an unstackable item
  # Changing the material will make existing nuggets unable to be combined back into a diamond
  #
  # Nugget items cannot be accidentally placed, used by right-clicking, or used in crafting except for:
  # - Powering a beacon
  # - Using an item as a brewing stand ingredient
  # - Fueling a brewing stand with blaze powder
  # - Mobs being attracted to and picking up items
  # - Shenanigans from other plugins
  # due to Spigot limitations
  #
  # See the link below for a list of possible materials
  # https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html
  # ECHO_SHARD (1.19+) recommended
  # Default is ECHO_SHARD
  material: ECHO_SHARD

  # ID of the custom model data in other words ID for the texture
  # For more info watch this video: https://www.youtube.com/watch?v=tV_yollZ8uM
  # Default is 5648554
  customModelData: 5648554

  # The name of the diamond nugget item, must be 1-50 characters
  # Changing the name will make existing nuggets unable to be combined back into a diamond
  # unless the player renames the nuggets back to the new name
  # Use can also use Chat Colors used by letter '&'
  name: "&r&fDiamond Nugget"

  # The lore of the diamond nugget item, must be 1-50 characters on one line
  # Use can also use Chat Colors used by letter '&'
  # Default is true
  lored: true
  lore:
    - "&rThis is a Diamond Nugget"
    - "&rNot an Echo Shard!"

  # For enchantments use enchantment ids here:
  # https://www.digminecraft.com/lists/enchantment_list_pc.php
  # Use it like:
  # <name of the enchantment for example fortune>:<level of the enchantment>
  #
  # Default is false
  enchanted: false
  enchantments:
    - fortune:1

  # Item flags
  # Use those:
  # HIDE_ATTRIBUTES - Setting to show/hide Attributes like Damage
  # HIDE_DESTROYS - Setting to show/hide what the ItemStack can break/destroy
  # HIDE_DYE - Setting to show/hide dyes from coloured leather armour
  # HIDE_ENCHANTS - Setting to show/hide enchants
  # HIDE_PLACED_ON - Setting to show/hide where this ItemStack can be build/placed on
  # HIDE_POTION_EFFECTS - Setting to show/hide potion effects, book and firework information, map tooltips, patterns of banners, and enchantments of enchanted books.
  # HIDE_UNBREAKABLE - Setting to show/hide the unbreakable State
  # Default is true
  flagged: true
  flags:
    - HIDE_ENCHANTS
    - HIDE_UNBREAKABLE
    - HIDE_ATTRIBUTES

# The amount of nuggets needed to craft a diamond
# Must be between 1-9, 4 or 9 is recommended
# 1-4 nuggets will fit in the 2x2 crafting grid
# All recipes are shaped due to vanilla limitations
nuggets_to_diamond: 9

# Whether the recipes unlock on join (or plugin enable) regardless of what items the player has
# If false, players will unlock the recipes when picking up a diamond or diamond nugget
# True by default
unlock_recipe_on_join: true

# Whether to stop players from trying to rename diamond nuggets
# Due to Minecraft recipe limitations, the *exact* item NBT must match for a custom recipe to succeed,
# so renaming the item will cause it to not work anymore in recipes
# Players can still rename regular golden nuggets, but these cannot be used to craft diamonds
# True by default
prevent_nugget_rename: true

# The message to display when a player is stopped from renaming diamond nuggets
rename_disabled_message: "Renaming diamond nuggets is disabled due to Minecraft recipe limitations"

# Whether to stop players from crafting other items with diamond nuggets
prevent_nugget_crafting: true

# Whether to completely stop players from placing nuggets in a
# stonecutter, cartography table, loom, anvil, grindstone, or villager trading menu.
# This will also prevent placement in brewing stands, but players can bypass it by glitching.
#
# When true, players will not be able to place nuggets in the above menus, but won't be able to shift click
# nuggets while one of these menus is open, even if the nugget would normally be shifted into the hotbar.
# When false, the crafting/trading output will appear if the nugget forms a valid recipe, but players
# will not be able to click the result and put it into their inventory.
# Note that it is possible for players to glitch nuggets into the inventory, but cannot get the crafting result.
#
# Set to false if you are using one of the recommended nugget items.
prevent_nugget_placement: false
```
