package net.lighttz.plugins.join;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import net.lighttz.plugins.Main;
import net.lighttz.plugins.chats.DesativarChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class JoinMensagem implements Listener {

    private static TitleManagerAPI api = (TitleManagerAPI) Bukkit.getServer ().getPluginManager ().getPlugin ("TitleManager");



    @EventHandler
    public void aoEntrar(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player p = (Player) e.getPlayer ();

        //Scoreboard

        new BukkitRunnable () {

        @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    Scoreboard score = Bukkit.getScoreboardManager().getNewScoreboard();
                    Objective obj = score.registerNewObjective("Status", "Status");
                    obj.setDisplayName("§c§lREDE ETERNITY");
                    obj.setDisplaySlot(DisplaySlot.SIDEBAR);

                    obj.getScore( "    §fJogadores online " + Bukkit.getOnlinePlayers().size()).setScore(8);
                    obj.getScore("§f ").setScore(7);
                    obj.getScore("§aNick: §f" + p.getName ()).setScore(6);
                    obj.getScore("§aRank: §f").setScore(5);
                    obj.getScore("§f§f ").setScore(4);
                    obj.getScore("§aMoney: ").setScore(3);
                    obj.getScore("§aCash: §f").setScore(2);
                    obj.getScore("§f§f§f§f").setScore(1);
                    obj.getScore(" §cloja.redeeternity.com ").setScore(0);
                    p.setScoreboard(score);
        }
    }
        }.runTaskTimer(Main.getPlugin(Main.class), 60, 20);

        //setupScoreboard();
        for (int i = 0; i<160; i++) {
            p.sendMessage(" ");
        }
        p.sendMessage("");
        p.sendMessage("                        §c§lREDE ETERNITY");
        p.sendMessage("");
        p.sendMessage("              §eOlá, §a"+ p.getDisplayName() + "§e, você está no §e§lLobby");
        p.sendMessage("            §eSeja bem vindo(a) ao nosso servidor!");
        p.sendMessage("");
        p.sendMessage("                  §cdiscord.gg/redeeternity");
        p.sendMessage("                    §cloja.redeeternity.com");
        api.sendActionbar (p, "§aPortuguês (BR)");
        api.sendTitle(p, "§c§lREDE ETERNITY");
        api.sendSubtitle(p, "§e§lSEJA BEM VINDO");
        p.sendMessage("");
        p.sendMessage("");


    }
    @EventHandler
    public void aoSair(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    public void setupScoreboard(){
        new BukkitRunnable () {

            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    api.setScoreboardTitle (p, "  §c§lREDE ETERNITY  ");
                    api.setScoreboardValue (p, 1, "    §fJogadores online " + Bukkit.getOnlinePlayers().size());
                    api.setScoreboardValue (p, 2, "");
                    api.setScoreboardValue (p, 3, p.getDisplayName());
                    api.setScoreboardValue (p, 4, "");
                    api.setScoreboardValue (p, 5, " §aRank: §f");
                    api.setScoreboardValue (p, 6, " §aMoney: §f0");
                    api.setScoreboardValue (p, 7, "");
                    api.setScoreboardValue (p, 8, " §floja.redeeternity.com ");
                    api.giveScoreboard (p);
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0, 1);
    }

}