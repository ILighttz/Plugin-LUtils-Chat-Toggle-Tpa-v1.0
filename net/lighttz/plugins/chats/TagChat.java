package net.lighttz.plugins.chats;

import com.mojang.authlib.BaseUserAuthentication;
import net.lighttz.plugins.Main;
import net.minecraft.server.v1_8_R3.PlayerList;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TagChat implements Listener {


    @EventHandler
    public void aoEntrar(PlayerJoinEvent e) {
        new BukkitRunnable () {

            @Override
            public void run() {
                Player p = (Player) e.getPlayer ();
                if (p.hasPermission ("tag.desenvolvedor")) {
                    p.setDisplayName ("§b[Desenvolvedor] ");
                    p.setPlayerListName ("§b[Desenvolvedor] " + p.getName ());
                } else {
                    p.setDisplayName ("§7");
                    p.setPlayerListName ("§7" + p.getName ());
                }
            }
        }.runTaskTimer (Main.getPlugin (Main.class), 1, 1);
    }


    @EventHandler
    public void Chat(AsyncPlayerChatEvent e) {
        Player p = (Player) e.getPlayer ();
        if (Bukkit.getOnlinePlayers ().size () > 1) {
        for (Entity ps : p.getNearbyEntities (10, 10, 10)) {
                if (ps instanceof Player) {
                    ((Player) ps).sendMessage ("§e[l] " + e.getPlayer ().getPlayerListName () + "§7:§e " + e.getMessage ());
                    p.sendMessage ("§e[l] " + e.getPlayer ().getPlayerListName () + "§7:§e " + e.getMessage ());
                    e.setCancelled (true);
                }
                e.setCancelled (true);
            }
            } else {
                p.sendMessage ("§e[l] " + e.getPlayer ().getPlayerListName () + "§7:§e " + e.getMessage ());
                p.sendMessage ("§eNão há ninguém para escutar");
                e.setCancelled (true);
        }
        e.setCancelled (true);
    }
}



