package net.lightningblocks.org.frontend;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (label.equalsIgnoreCase("lblocks")) {
                if (player.hasPermission("lightningblocks.admin")) {

                    if (args[0].equalsIgnoreCase("give")) {

                        ItemStack lb = new ItemStack(Material.SEA_LANTERN);     ItemMeta meta = lb.getItemMeta();
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bLightning &3Block"));  lb.setItemMeta(meta);

                        player.getInventory().addItem(lb);
                    }
                }
            }
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }

}
