package net.lighttz.plugins.chats;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import net.lighttz.plugins.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.ArrayList;
import java.util.Arrays;

public class DesativarChat implements CommandExecutor {
    private static TitleManagerAPI api = (TitleManagerAPI) Bukkit.getServer ().getPluginManager ().getPlugin ("TitleManager");

    public static boolean enableChat = true;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {


        enableChat = !(enableChat);
        Player p = (Player)sender;


        if (cmd.getName().equalsIgnoreCase("chat")) {
            if (sender.hasPermission("chatoff.re")) {
                if (args.length < 1) {
                    p.sendMessage("§cModo correto: /chat [ON/OFF]");
                    return true;
                }
                if (args[0].equalsIgnoreCase("OFF")) {
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage("§eO chat global foi desativado por " + p.getDisplayName() + p.getName());
                    Bukkit.broadcastMessage("");
                    api.sendActionbar(p, "§aVocê desativou o Chat Global");
                    enableChat = false;
                    return true;

                }
                if (args[0].equalsIgnoreCase("ON")) {
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage("§eO chat global foi ativado por " + p.getDisplayName() + p.getName());
                    Bukkit.broadcastMessage("");
                    api.sendActionbar(p, "§aVocê ativou o Chat Global");
                    enableChat = true;
                    return true;
                }
                return true;
            }
        }


        return false;
    }
}
