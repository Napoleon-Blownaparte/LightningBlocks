package net.lightningblocks.org.frontend;

import net.lightningblocks.org.backend.BlocksFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvents implements Listener {

    @EventHandler
    public void onLightBlockPlace(BlockPlaceEvent event){

        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bLightning &3Block"))){
            if (player.hasPermission("lightningblock.place")){

                Integer i = 2;
                for (String lb : BlocksFile.get().getConfigurationSection("lightningblocks").getKeys(false)){
                    i++;

                }
                BlocksFile.get().set("lightningblocks." + 2 + ".world", event.getBlock().getLocation().getWorld().getName());
                BlocksFile.get().set("lightningblocks." + 2 + ".x", event.getBlock().getLocation().getBlockX());
                BlocksFile.get().set("lightningblocks." + 2 + ".y", event.getBlock().getLocation().getBlockY());
                BlocksFile.get().set("lightningblocks." + 2 + ".z", event.getBlock().getLocation().getBlockZ());
                BlocksFile.save();

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lLightning&b&lBlocks &8| &7You added a new Lightning Block!"));

            }
        }
    }

}
