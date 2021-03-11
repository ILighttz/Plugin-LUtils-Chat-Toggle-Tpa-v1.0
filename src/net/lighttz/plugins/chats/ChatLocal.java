package net.lighttz.plugins.chats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatLocal implements Listener {


    @EventHandler
    public void Chat(AsyncPlayerChatEvent e) {
        Player p = (Player) e.getPlayer ();
        boolean perto = false;
        if (Bukkit.getOnlinePlayers ().size () > 1) {
            for (Entity ps : p.getNearbyEntities (25, 15, 25)) {
                if (ps instanceof Player) {
                        ((Player) ps).sendMessage("§e[l] " + e.getPlayer().getPlayerListName() + "§7:§e " + e.getMessage());
                        p.sendMessage("§e[l] " + e.getPlayer().getPlayerListName() + "§7:§e " + e.getMessage());
                        perto = true;
                        e.setCancelled(true);
                }
                e.setCancelled (true);
            }
            if (!perto){
                p.sendMessage ("§e[l] " + e.getPlayer ().getPlayerListName () + "§7:§e " + e.getMessage ());
                p.sendMessage ("§eNão há ninguém para escutar");
            }
        } else {
            p.sendMessage ("§e[l] " + e.getPlayer ().getPlayerListName () + "§7:§e " + e.getMessage ());
            p.sendMessage ("§eNão há ninguém para escutar");
            e.setCancelled (true);
        }
        e.setCancelled (true);
    }

}
