package net.lighttz.plugins.chats;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import net.lighttz.plugins.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class ChatGlobal implements CommandExecutor {
    private HashMap<UUID, Date> times = new HashMap<>();
    private static TitleManagerAPI api = (TitleManagerAPI) Bukkit.getServer ().getPluginManager ().getPlugin ("TitleManager");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        boolean perto = false;
        if (!(sender instanceof Player)){
            sender.sendMessage("§cChat disponivel para jogadores");
            return true;
        }
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("g")){
            if (args.length <= 0) {
                p.sendMessage ("§cModo correto: /g (Mensagem)");
                return true;
            }
            if (!DesativarChat.enableChat) {
                p.sendMessage("§cO Chat Global está desativado");
                return false;
            }
                if (this.times.containsKey(p.getUniqueId()) && ((Date)this.times.get(p.getUniqueId())).after(new Date())) {
                    api.sendActionbar (p, "§cVocê está escrevendo muito rapido!");
                } else {
                    this.times.remove(p.getUniqueId());
                    String Mensagem = String.join(" ", (CharSequence[])args);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (!Main.globalCache.contains(all.getUniqueId())) {
                                if (p.hasPermission("chatcolor.re")) {
                                    all.sendMessage("§7[G] " + p.getDisplayName() + p.getName() + "§7: " + ChatColor.translateAlternateColorCodes('&', Mensagem));
                                    if (Mensagem.contains(all.getName())) {
                                        if (Main.soundCache.contains(all.getUniqueId())){
                                            all.playSound(all.getLocation(), Sound.SHOOT_ARROW, 10, 1);
                                            all.sendMessage("§eVocê foi mencionado no Chat Global");
                                        }

                                    }
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.add(13, 5);
                                    this.times.put(p.getUniqueId(), calendar.getTime());
                                } else {
                                    all.sendMessage("§7[G] " + p.getDisplayName() + p.getName() + "§7: " + Mensagem);
                                    if (Mensagem.contains(all.getName())) {
                                        if (Main.soundCache.contains(all.getUniqueId())){
                                            all.playSound(all.getLocation(), Sound.SHOOT_ARROW, 10, 1);
                                            all.sendMessage("§eVocê foi mencionado no Chat Global");
                                        }
                                    }
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.add(13, 5);
                                    this.times.put(p.getUniqueId(), calendar.getTime());
                                }
                        }
                    }
                }

            return true;
        }
        return false;
    }
}
