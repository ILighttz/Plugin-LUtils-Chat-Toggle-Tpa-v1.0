package net.lighttz.plugins.chats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatStaff implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage("§cApenas para jogadores");
        }

        String Mensagem = String.join(" ", (CharSequence[])args);
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("s")){
            if (sender.hasPermission("staffchat.re")){
                for (Player staff : Bukkit.getOnlinePlayers()){
                    if (staff.hasPermission("staffchat.re")){
                        staff.sendMessage(ChatColor.LIGHT_PURPLE + "[Staff] " + p.getDisplayName() + p.getName() + ChatColor.LIGHT_PURPLE + ": " + Mensagem);
                    }
                }

            }


        }





        return false;
    }
}
