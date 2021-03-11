package net.lighttz.plugins.chats;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Locale;


public class ChatEvent implements Listener {
    private static TitleManagerAPI api = (TitleManagerAPI) Bukkit.getServer ().getPluginManager ().getPlugin ("TitleManager");

    @EventHandler
    public void mensagem(AsyncPlayerChatEvent e) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (e.getMessage().startsWith("/g")) {
                if (e.getMessage().contains(all.getName()))
                all.playSound(all.getLocation(), Sound.SHOOT_ARROW, 10, 1);
                all.sendMessage("§eVocê foi mencionado no Chat Global");
                return;
            }
        }
        }
}
