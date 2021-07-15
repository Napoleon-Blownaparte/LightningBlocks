package net.lightningblocks.org.frontend;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (label.equalsIgnoreCase("lblocks")) {
                if (player.hasPermission("lightningblocks.admin")) {
                }
                if (args[0].equalsIgnoreCase("give")) {

                } else {

                }
            }
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }

}
