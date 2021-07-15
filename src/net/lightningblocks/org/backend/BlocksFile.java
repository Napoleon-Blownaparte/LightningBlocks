package net.lightningblocks.org.backend;

import net.lightningblocks.org.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.Boolean.TRUE;

public class BlocksFile {

    private static Plugin plugin = Main.getPlugin(Main.class);

    public static FileConfiguration get(){

        File file = new File(plugin.getDataFolder(), "blocks.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(file);

        return yml;

    }

    public static void save(){

        File file = new File(plugin.getDataFolder(), "blocks.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(file);

        // If file exists, save the file
        if (file.exists()){
            try {
                yml.save(file);
                System.out.println("[LightningBlocks] Successfully saved file 'blocks.yml'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // If file doesn't exist, create new file and contents

            try {
                file.createNewFile();
                yml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
