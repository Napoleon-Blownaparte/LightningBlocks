package net.lightningblocks.org;

import net.lightningblocks.org.backend.BlocksFile;
import net.lightningblocks.org.frontend.BlockCommands;
import net.lightningblocks.org.frontend.BlockEvents;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static void summon(){
        for (String lb : BlocksFile.get().getConfigurationSection("lightningblocks").getKeys(false)){

            String world = BlocksFile.get().getString("lightningblocks." + lb + ".world");
            int X = BlocksFile.get().getInt("lightningblocks." + lb + ".x");
            int Y = BlocksFile.get().getInt("lightningblocks." + lb + ".Y");
            int Z = BlocksFile.get().getInt("lightningblocks." + lb + ".Z");

            Block b = Bukkit.getServer().getWorld(world).getBlockAt(X, Y, Z);

            Bukkit.getWorld(world).strikeLightningEffect(b.getLocation());
            Bukkit.getWorld(world).spawnParticle(Particle.SOUL_FIRE_FLAME, b.getLocation(), 200);

        }
    }

    public void onEnable(){

        // Save files
        BlocksFile.save();

        // Register Events
        getServer().getPluginManager().registerEvents(new BlockEvents(), this);

        // Register Commands
        this.getCommand("lblocks").setExecutor(new BlockCommands());

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (BlocksFile.get().getConfigurationSection("lightningblocks") != null) {
                    summon();
                }
            }
        }, 200, 100);

    }

    public void onDisable(){

    }


}
