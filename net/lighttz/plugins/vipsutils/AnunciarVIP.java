package net.lighttz.plugins.vipsutils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnunciarVIP implements CommandExecutor {
    private HashMap<UUID, Date> times = new HashMap<>();
    private static TitleManagerAPI api = (TitleManagerAPI) Bukkit.getServer ().getPluginManager ().getPlugin ("TitleManager");

    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para jogadores");
            return true;
        }
        Player p = (Player)sender;
        if (!p.hasPermission("anunciar.re")) {
            p.sendMessage("§cExclusivo para §eVIPs§c. Adquira o seu em redeeterinity.com");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("anunciar")) {
            if (args.length <= 0) {
                p.sendMessage("§cModo correto: /anunciar (Mensagem)");
                return true;
            }
            if (this.times.containsKey(p.getUniqueId()) && ((Date)this.times.get(p.getUniqueId())).after(new Date())) {
                api.sendActionbar(p,"§cAguarde 5 minutos para utilizar novamente");
            } else {
                this.times.remove(p.getUniqueId());
                String Mensagem = String.join(" ", (CharSequence[])args);
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("§cO jogador §f" + p.getName() + " §cestá divulgando sua loja!!");
                Bukkit.broadcastMessage("§c[Anuncio] "+ ChatColor.translateAlternateColorCodes('&', Mensagem));
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("§c/loja §f" + p.getName());
                Bukkit.broadcastMessage("");
                Calendar calendar = Calendar.getInstance();
                calendar.add(12, 5);
                this.times.put(p.getUniqueId(), calendar.getTime());
            }
        }
        return false;
    }
}
