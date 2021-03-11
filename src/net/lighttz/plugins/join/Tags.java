package net.lighttz.plugins.join;

import net.lighttz.plugins.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Tags implements Listener {

    @EventHandler
    public void aoEntrar(PlayerJoinEvent e) {
        new BukkitRunnable() {

            @Override
            public void run() {
                Player p = (Player) e.getPlayer ();
                if (p.hasPermission ("tag.desenvolvedor")) {
                    p.setDisplayName ("§b[Desenvolvedor] ");
                    p.setPlayerListName ("§b[Desenvolvedor] " + p.getName ());
                } else if (p.hasPermission("tag.diretor")){
                    p.setDisplayName ("§6[Diretor]");
                    p.setPlayerListName ("§6[Diretor]" + p.getName ());
                } else if (p.hasPermission("tag.gerente")){
                    p.setDisplayName ("§4[Gerente]");
                    p.setPlayerListName ("§4[Gerente]" + p.getName ());
                } else if (p.hasPermission("tag.admin")){
                    p.setDisplayName ("§c[Administrador]");
                    p.setPlayerListName ("§c[Administrador]" + p.getName ());
                } else if (p.hasPermission("tag.mod")){
                    p.setDisplayName ("§2[Moderador]");
                    p.setPlayerListName ("§2[Moderador]" + p.getName ());
                } else if (p.hasPermission("tag.ajudante")){
                    p.setDisplayName ("§e[Ajudante]");
                    p.setPlayerListName ("§e[Ajudante]" + p.getName ());
                } else if (p.hasPermission("tag.vip3")){
                    p.setDisplayName ("§6[VIP3]");
                    p.setPlayerListName ("§6[VIP3]" + p.getName ());
                } else if (p.hasPermission("tag.vip2")){
                    p.setDisplayName ("§6[VIP2]");
                    p.setPlayerListName ("§6[VIP2]" + p.getName ());
                } else if (p.hasPermission("tag.vip1")){
                    p.setDisplayName ("§6[VIP1]");
                    p.setPlayerListName ("§6[VIP1]" + p.getName ());
                } else {
                    p.setDisplayName ("§7");
                    p.setPlayerListName ("§7" + p.getName ());
                }
            }
        }.runTaskTimer (Main.getPlugin (Main.class), 1, 1);
    }
    
}
