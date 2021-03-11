package net.lighttz.plugins.teleport;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TP implements CommandExecutor {
    private static TitleManagerAPI api = (TitleManagerAPI) Bukkit.getServer ().getPluginManager ().getPlugin ("TitleManager");
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("§Comando apenas para jogadores");
            return true;
        }
        if (sender.hasPermission ("tp.re")) {
            Player p = (Player) sender;
            if (cmd.getName ().equalsIgnoreCase ("tp")) {
                if (args.length < 1) {
                    p.sendMessage ("§cModo correto: /tp (jogador)");
                    return true;
                }

                Player t = Bukkit.getPlayer (args[0]);
                OfflinePlayer target = Bukkit.getOfflinePlayer (args[0]);

                if (args.length == 2) {
                    Player t2 = Bukkit.getPlayer (args[1]);
                    OfflinePlayer target2 = Bukkit.getOfflinePlayer (args[1]);
                    if (args[0].equalsIgnoreCase (args[1])) {
                        api.sendActionbar (p, "§cVocê não pode teleportar o mesmo jogador até ele mesmo!");
                        return true;
                    }
                    if (target.hasPlayedBefore ()) {
                        if (target2.hasPlayedBefore ()) {
                            if (target.isOnline ()) {
                                if (target2.isOnline ()) {
                                    api.sendActionbar (t2, t.getDisplayName () + t.getName () + " §afoi teleportado até você!");
                                    api.sendActionbar (t, "§aVocê foi teleportado até o(a) " + t2.getDisplayName () + t2.getName ());
                                    t.teleport (t2.getLocation ());
                                } else {
                                    api.sendActionbar (p, "§cUm dos jogadores está offline");
                                }
                            } else {
                                api.sendActionbar (p, "§cUm dos jogadores está offline");
                            }


                        } else {
                            api.sendActionbar (p, "§cUm dos jogadores não existe");
                            return true;
                        }

                    } else {
                        api.sendActionbar (p, "§cUm dos jogadores não existe");
                        return true;
                    }
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase (p.getName ())) {
                        api.sendActionbar (p, "§cVocê não pode se teleportar até você mesmo!");
                        return true;
                    }
                    if (target.hasPlayedBefore ()) {
                        if (target.isOnline ()) {
                            api.sendActionbar (p, "§aVocê se teleportou até o(a) " + t.getDisplayName () + t.getName ());
                            p.teleport (t.getLocation ());
                        } else {
                            api.sendActionbar (p, "§cJogador está offline");
                        }
                    } else {
                        api.sendActionbar (p, "§cJogador não existe");
                        return true;
                    }

                }
            }
            }

        return false;
    }
}
