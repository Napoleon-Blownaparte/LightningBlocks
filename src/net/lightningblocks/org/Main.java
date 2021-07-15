package net.lightningblocks.org;

import net.lightningblocks.org.backend.BlocksFile;
import net.lightningblocks.org.frontend.BlockCommands;
import net.lightningblocks.org.frontend.BlockEvents;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable(){

        // Save files
        BlocksFile.save();

        // Register Events
        getServer().getPluginManager().registerEvents(new BlockEvents(), this);

        // Register Commands
        this.getCommand("lblocks").setExecutor(new BlockCommands());

    }

    public void onDisable(){

    }


}
