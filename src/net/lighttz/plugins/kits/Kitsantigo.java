package net.lighttz.plugins.kits;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Kitsantigo implements CommandExecutor, Listener {
    public boolean onCommand(CommandSender sender, Command cmd, String b, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando para jogadores");
            return true;
        }
        Player p = (Player)sender;
        Inventory inv = Bukkit.createInventory(null, 27, "§7Kits");
        if (cmd.getName().equalsIgnoreCase("kits")) {
            p.openInventory(inv);

            ItemStack item = new ItemStack(Material.IRON_SWORD);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName("§cKit PvP");
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§aClique para adquirir seu kit");
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            inv.setItem(11, item);

            ItemStack vip = new ItemStack(Material.OBSIDIAN);
            ItemMeta itemMetaVIP = vip.getItemMeta();
            itemMetaVIP.setDisplayName("§bKits VIPS");
            ArrayList<String> lorevip = new ArrayList<>();
            lorevip.add("§aClique para acessar o menu dos kits");
            itemMetaVIP.setLore(lorevip);
            vip.setItemMeta(itemMetaVIP);
            inv.setItem(13, vip);
        }
        return false;
    }

    @EventHandler
    public void aoClicar(InventoryClickEvent e) {
        //aba 1
        Inventory inv = Bukkit.createInventory(null, 27, "§7Kits");

        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§cKit PvP");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§aClique para adquirir seu kit");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        inv.setItem(11, item);

        ItemStack vip = new ItemStack(Material.OBSIDIAN);
        ItemMeta itemMetaVIP = vip.getItemMeta();
        itemMetaVIP.setDisplayName("§bKits VIPS");
        ArrayList<String> lorevip = new ArrayList<>();
        lorevip.add("§aClique para acessar o menu dos kits");
        itemMetaVIP.setLore(lorevip);
        vip.setItemMeta(itemMetaVIP);
        inv.setItem(13, vip);

        //aba 2
        Inventory invvip = Bukkit.createInventory(null, 27, "§7Kits VIPS");
        ItemStack itemvipdiario = new ItemStack(Material.NETHER_BRICK);
        ItemMeta itemMetaVIPDIARIO = itemvipdiario.getItemMeta();
        itemMetaVIPDIARIO.setDisplayName("§eVIP Diário");
        lore.add("§aClique para adquirir seu kit");
        itemMetaVIPDIARIO.setLore(lore);
        itemvipdiario.setItemMeta(itemMetaVIPDIARIO);
        invvip.setItem(11, itemvipdiario);

        Player p = (Player)e.getWhoClicked();
        if (e.getWhoClicked() instanceof Player &&
                e.getInventory().getTitle().equals("§7Kits")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.AIR){
                e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cKit PvP")) {
                if (e.getWhoClicked().hasPermission("re.kitpvp")) {
                    p.sendMessage("§aO seu Kit PvP foi adquirido com sucesso");
                    p.closeInventory();
                } else {
                    p.sendMessage("§cVocê não tem permissão para pegar esse KIT!");
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bKits VIPS")) {
                p.openInventory(invvip);
                if (e.getInventory().getTitle().equals("§7Kits VIPS")) {
                    e.setCancelled(true);
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eVIP Diário"))
                        if (e.getWhoClicked().hasPermission("re.vip1diario")) {
                            p.sendMessage("§aO seu Kit VIP diário foi adquirido com sucesso");
                            p.closeInventory();
                        } else {
                            p.sendMessage("§cKit exclusivo para §eVIPs§c. Adquira o seu em redeeterinity.com");
                        }
                }
            }
        }
    }
}
}
