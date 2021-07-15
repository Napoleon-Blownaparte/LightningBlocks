package net.lightningblocks.org.frontend;

import net.lightningblocks.org.Main;
import net.lightningblocks.org.backend.BlocksFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class BlockEvents implements Listener {

    private static Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onLightBlockPlace(BlockPlaceEvent event){

        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bLightning &3Block"))){
            if (player.hasPermission("lightningblock.place")){

                int id = 1 + (int)(Math.random() * ((1000 - 1) + 1));

                File file = new File(plugin.getDataFolder(), "blocks.yml");
                FileConfiguration yml = YamlConfiguration.loadConfiguration(file);

                yml.set("lightningblocks." + id + ".world", event.getBlock().getLocation().getWorld().getName());
                yml.set("lightningblocks." + id + ".x", event.getBlock().getLocation().getBlockX());
                yml.set("lightningblocks." + id + ".y", event.getBlock().getLocation().getBlockY());
                yml.set("lightningblocks." + id + ".z", event.getBlock().getLocation().getBlockZ());
                try {
                    yml.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                event.getBlock().setType(Material.AIR);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lLightning&b&lBlocks &8| &7You added a new Lightning Block!"));

            }
        }
    }

}
