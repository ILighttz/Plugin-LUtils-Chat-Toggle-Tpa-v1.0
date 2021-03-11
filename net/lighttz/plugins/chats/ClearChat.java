package net.lighttz.plugins.chats;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
    if (cmd.getName().equalsIgnoreCase("clearchat")) {
        Player p = (Player) sender;
        if (p.hasPermission("clearchat.re")) {
            for (int i = 0; i < 160; i++) {
                p.sendMessage(" ");
            }
            Bukkit.broadcastMessage("§eChat limpo por " + p.getDisplayName() + p.getName());

        }


    }
        return false;
    }
}
