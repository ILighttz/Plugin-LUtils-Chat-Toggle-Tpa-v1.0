package net.lighttz.plugins.chats;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import net.lighttz.plugins.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

import static org.bukkit.Bukkit.*;

public class ChatTell implements CommandExecutor{

    private static TitleManagerAPI api = (TitleManagerAPI) Bukkit.getServer ().getPluginManager ().getPlugin ("TitleManager");
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage ("§cChat disponivel para jogadores");
            return true;
        }
        Player p = (Player) sender;
        if (cmd.getName ().equalsIgnoreCase ("tell")) {
            if (args.length > 1){
                Player t = Bukkit.getPlayer (args[0]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (args[0].equalsIgnoreCase(p.getName())) {
                api.sendActionbar(p,"§cNão pode enviar mensagens para você mesmo!");
            } else {
                if(Main.chatCache.contains(t.getUniqueId())){
                    api.sendActionbar(p, "§cO jogador está com o Tell desativado");
                    return true;
                }
                    if (target.hasPlayedBefore()){
                if (p.getName () == t.getName ()) {
                    api.sendActionbar(p,"§cJogador está offline");
                } else {
                    String[] notarg = Arrays.copyOfRange (args, 1, args.length);
                    String Mensagem = String.join (" ", notarg);
                    if (!Main.soundCache.contains(t.getUniqueId())) {
                        t.playSound(t.getLocation(), Sound.LEVEL_UP, 10, 1);
                    }
                    p.sendMessage ("§e[Tell] Mensagem enviada para " + t.getDisplayName () + t.getName () + " §7➥ " + Mensagem);
                    t.sendMessage ("§e[Tell] Mensagem recebida de " + p.getDisplayName () + p.getName () + " §7➥ " + Mensagem);

                }
            } else {
                        api.sendActionbar(p,"§cJogador não existe");
                }
            }
                } else {
                p.sendMessage ("§cModo correto: /tell (jogador) (mensagem)");
            }

            }

        return false;
    }
    }
