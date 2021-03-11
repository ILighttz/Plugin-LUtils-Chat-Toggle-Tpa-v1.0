package net.lighttz.plugins.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Kits implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] argss) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComandos disponivel para jogadores");
            return true;
        }
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("kits")){
            Inventory inv = Bukkit.createInventory(null, 27, "§7Kits");
            p.openInventory(inv);

            ItemStack kitpvp = new ItemStack (Material.IRON_SWORD);
            ItemMeta kitpvpmeta = kitpvp.getItemMeta();
            kitpvpmeta.setDisplayName("§eKit PvP");
            ArrayList<String> lorekitpvp = new ArrayList<>();
            ArrayList<String> lorespace = new ArrayList<>();
            lorekitpvp.add("\n§aClique para adquirir");
            kitpvpmeta.setLore(lorekitpvp);
            kitpvp.setItemMeta(kitpvpmeta);


            inv.setItem(11, kitpvp);
            inv.setItem(13, new ItemStack (Material.DIAMOND_BLOCK));
            inv.setItem (15, new ItemStack (Material.EMERALD));

            return true;
        }
        return false;
    }
}
