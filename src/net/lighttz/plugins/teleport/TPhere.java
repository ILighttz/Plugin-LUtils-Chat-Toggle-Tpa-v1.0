package net.lighttz.plugins.teleport;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TPhere implements CommandExecutor, Listener {
    private static TitleManagerAPI api = (TitleManagerAPI) Bukkit.getServer ().getPluginManager ().getPlugin ("TitleManager");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage ("§Comando apenas para jogadores");
            return true;
        }
        Player p = (Player) sender;

        if (cmd.getName ().equalsIgnoreCase ("tphere")) {
            if (sender.hasPermission ("tphere.re")) {
                if (args.length < 1) {
                    p.sendMessage ("§cModo correto: /tphere (jogador)");
                    return true;
                }
                Player t = Bukkit.getPlayer (args[0]);
                OfflinePlayer target = Bukkit.getOfflinePlayer (args[0]);
                if (!(args[0].equalsIgnoreCase (p.getName ()))) {
                    api.sendActionbar (p, "§cVocê não pode puxar você mesmo!");
                    if (Bukkit.getServer ().getOnlinePlayers ().size () > 1) {
                        if (args[0].equalsIgnoreCase ("all")) {
                            for (Player alls : Bukkit.getServer ().getOnlinePlayers ()) {
                                api.sendActionbar (alls, "§aVocê foi puxado até o(a) " + p.getDisplayName () + p.getName ());
                                alls.teleport (p.getLocation ());
                            }
                            api.sendActionbar (p, "§aVocê puxou " + (Bukkit.getServer ().getOnlinePlayers ().size () - 1) + " jogadores");
                        } else if (args[0].equalsIgnoreCase (p.getName ())) {
                            api.sendActionbar (p, "§cVocê não pode puxar você mesmo!");
                        } else if (target.hasPlayedBefore ()) {
                            if (target.isOnline ()) {
                                api.sendActionbar (t, "§aVocê foi puxado até o(a) " + p.getDisplayName () + p.getName ());
                                api.sendActionbar (p, "§aVocê puxou o(a) " + t.getDisplayName () + t.getName ());
                                t.teleport (p.getLocation ());
                            } else {
                                api.sendActionbar (p, "§cJogador está offline");
                            }
                        } else {
                            api.sendActionbar (p, "§cJogador não existente");
                        }

                    } else {
                        api.sendActionbar (p, "§cNão há jogadores para serem puxados");
                    }
                } else {
                    api.sendActionbar (p, "§cVocê não pode puxar você mesmo!");
                }
            }
            } return false;
        }
    }
