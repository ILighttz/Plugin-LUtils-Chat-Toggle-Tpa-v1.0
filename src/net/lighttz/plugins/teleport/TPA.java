package net.lighttz.plugins.teleport;

import com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl;
import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import javafx.scene.text.TextBuilder;
import net.lighttz.plugins.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TPA implements CommandExecutor, Listener {
    private static TitleManagerAPI api = (TitleManagerAPI) Bukkit.getServer ().getPluginManager ().getPlugin ("TitleManager");
    public static Map<Player, Player> tpa = new HashMap<>();
    public ArrayList<String> noTpa = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§Comando apenas para jogadores");
            return true;
        }
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("tpa")) {
            if (args.length < 1) {
                p.sendMessage("§cModo correto: /tpa (jogador)");
                return true;
            }

            Player t = Bukkit.getPlayer(args[0]);
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

            if (args[0].equalsIgnoreCase(p.getName())) {
                api.sendActionbar(p, "§cVocê não pode se teleportar até você mesmo!");
                return true;
            }


            if (t == null) {
                if (args[0].equalsIgnoreCase("aceitar")) {
                    final Player ta = tpa.get(p);
                    if (ta == p){
                        api.sendActionbar(p,"§cVocê não pode se teleportar até você mesmo!");
                        return true;
                    }
                    if (ta == null) {
                        api.sendActionbar(p, "§cVocê não possui nenhuma solicitação de teleporte");
                        return true;
                    }

                        api.sendActionbar(p, "§aVocê aceitou o tpa de " + ta.getDisplayName() + ta.getName() + "§a.");
                        api.sendActionbar(ta, "§a" + p.getDisplayName() + p.getName() + " §aaceitou seu pedido de teleporte!");
                        ta.sendMessage("");
                        ta.sendMessage("§eVocê será teleportado em 5 segundos");
                        ta.sendMessage("");

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (ta != null && p != null) {

                                    ta.teleport(p.getLocation());
                                }
                                tpa.remove(p);
                            }
                        }.runTaskLater(Main.getPlugin(Main.class), 100);
                    ta.sendMessage("");
                    ta.sendMessage("§eVocê foi teleportado até "+ p.getDisplayName() + p.getName() + "!");
                    ta.sendMessage("");
                        return true;
                    } else if (args[0].equalsIgnoreCase("negar")) {
                        Player ta = tpa.get(p);
                        if (ta == null) {
                            api.sendActionbar(p, "§cVocê não tem nenhuma solicitação de tpa.");
                            return true;
                        }

                        api.sendActionbar(p, "§eVocê negou o tpa de " + ta.getDisplayName() + ta.getName() + "§c.");
                        api.sendActionbar(ta, p.getDisplayName() + p.getName() + " §enegou seu pedido de tpa.");
                        tpa.remove(p);
                        return true;
                }
                api.sendActionbar(p,"§cEste jogador está offline!");
                return true;
            }

            if(Main.tpaCache.contains(t.getUniqueId())){
                api.sendActionbar(p, "§cO jogador está com os pedidos de TPA desativados");
                return true;
            }

            if (noTpa.contains(p.getName())) {
                api.sendActionbar(p, "§cVocê deve aguardar um pouco para enviar outro pedido de tpa.");
                return true;
            }

            tpa.put(t, p);
            api.sendActionbar(p, "§eVocê solicitou um pedido de tpa para " + t.getDisplayName() + t.getName() + "§a.");
            noTpa.add(p.getName());
            new BukkitRunnable() {

                @Override
                public void run() {
                    noTpa.remove(p.getName());
                }
            }.runTaskLater(Main.getPlugin(Main.class), 1200);
            if (t == p) {
                api.sendActionbar(p,"§cVocê não pode se teleportar até você mesmo!");
                return true;
            }
                t.sendMessage("");
                t.sendMessage("§ePedido de teleporte recebido de " + p.getDisplayName() + p.getName());
                t.sendMessage("");
                t.sendMessage("§a/tpa aceitar §epara aceitar ou §c/tpa negar §epara negar.");
                t.sendMessage("");
                api.sendActionbar(t, p.getDisplayName() + p.getName() + " §esolicitou um pedido de tpa para você.");



        }
        return false;


    }

    }
