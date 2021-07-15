package net.lightningblocks.org;

import net.lightningblocks.org.backend.BlocksFile;
import net.lightningblocks.org.frontend.BlockCommands;
import net.lightningblocks.org.frontend.BlockEvents;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private void summon(){

        File file = new File(this.getDataFolder(), "blocks.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(file);

        for (String lb : yml.getConfigurationSection("lightningblocks").getKeys(false)){

            String world = yml.getString("lightningblocks." + lb + ".world");
            int X = yml.getInt("lightningblocks." + lb + ".x");
            int Y = yml.getInt("lightningblocks." + lb + ".y");
            int Z = yml.getInt("lightningblocks." + lb + ".z");

            Block b = Bukkit.getServer().getWorld(world).getBlockAt(X, Y, Z);

            Bukkit.getWorld(world).strikeLightningEffect(b.getLocation());
            Bukkit.getWorld(world).spawnParticle(Particle.SOUL_FIRE_FLAME, b.getLocation(), 200);

        }
    }

    public void onEnable(){

        File file = new File(this.getDataFolder(), "blocks.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(file);

        // Save files
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register Events
        getServer().getPluginManager().registerEvents(new BlockEvents(), this);

        // Register Commands
        this.getCommand("lblocks").setExecutor(new BlockCommands());

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (yml.getConfigurationSection("lightningblocks") != null) {
                    summon();
                }
            }
        }, 200, 100);

    }

    public void onDisable(){

    }


}
