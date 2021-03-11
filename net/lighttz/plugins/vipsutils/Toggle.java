package net.lighttz.plugins.vipsutils;

import com.mojang.authlib.GameProfile;
import net.lighttz.plugins.Main;
import net.minecraft.server.v1_8_R3.TileEntitySkull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Dye;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Predicate;

public class Toggle implements Listener, CommandExecutor {

    ArrayList<String> OffTpa = new ArrayList<>();
    ArrayList<String> OffG = new ArrayList<>();
    ArrayList<String> OffTell = new ArrayList<>();


    public static ItemStack getItem(String name, boolean enable) {

        Dye dye = new Dye();
        if (!enable) {
            ItemStack stack = new ItemStack(Material.BED);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§a§l" + name + " ATIVADO");
            meta.setLore(Arrays.asList("§eClique para desativar os pedidos de TPA", "", "§7Exclusivo VIP"));
            stack.setItemMeta(meta);
            return stack;

        }
        //ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemStack stack = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§c§l" + name + " DESATIVADO");
        meta.setLore(Arrays.asList("§eClique para ativar os pedidos de TPA", "", "§7Exclusivo VIP"));
        stack.setItemMeta(meta);
        return stack;
    }

    public static ItemStack getItem2(String name, boolean enable) {

        Dye dye = new Dye();
        if (!enable) {
            ItemStack stack2 = new ItemStack(Material.SIGN);
            ItemMeta meta2 = stack2.getItemMeta();
            meta2.setDisplayName("§a§l" + name + " ATIVADO");
            meta2.setLore(Arrays.asList("§eClique para desativar recebimento de mensagens no Tell", ""));
            stack2.setItemMeta(meta2);
            return stack2;

        }
        //ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemStack stack2 = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta meta2 = stack2.getItemMeta();
        meta2.setDisplayName("§c§l" + name + " DESATIVADO");
        meta2.setLore(Arrays.asList("§eClique para ativar recebimento de mensagens no Tell", ""));
        stack2.setItemMeta(meta2);
        return stack2;
    }

    public static ItemStack getItem3(String name, boolean enable) {

        Dye dye = new Dye();
        if (!enable) {
            ItemStack stack = new ItemStack(Material.GRASS);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§a§l" + name + " ATIVADO");
            meta.setLore(Arrays.asList("§eClique para desativar recebimento de mensagens no ChatGlobal", "", "§7Exclusivo VIP"));
            stack.setItemMeta(meta);
            return stack;

        }
        //ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemStack stack = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§c§l" + name + " DESATIVADO");
        meta.setLore(Arrays.asList("§eClique para ativar recebimento de mensagens no ChatGlobal", "", "§7Exclusivo VIP"));
        stack.setItemMeta(meta);
        return stack;
    }

    public static ItemStack getItem4(String name, boolean enable) {

        Dye dye = new Dye();
        if (!enable) {
            ItemStack stack = new ItemStack(Material.JUKEBOX);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§a§l" + name + " ATIVADO");
            meta.setLore(Arrays.asList("§eClique para desativar recebimento de SONS por comandos", ""));
            stack.setItemMeta(meta);
            return stack;

        }
        //ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemStack stack = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§c§l" + name + " DESATIVADO");
        meta.setLore(Arrays.asList("§eClique para ativar recebimento de SONS por comandos", ""));
        stack.setItemMeta(meta);
        return stack;
    }




    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§Comando apenas para jogadores");
            return true;
        }
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("toggle")) {
                    Inventory inv = Bukkit.createInventory(null, 45, "§7Ativar/Desativar Funções");

                    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

                    SkullMeta metaskull = (SkullMeta) skull.getItemMeta();
                    metaskull.setOwner(p.getName());
                    metaskull.setDisplayName("§ePreferências de funções");
                    metaskull.setLore(Arrays.asList("", "§7Jogador: " + p.getDisplayName() + p.getName(), ""));
                    skull.setItemMeta(metaskull);




                    inv.setItem(4, skull);
                    inv.setItem(20, getItem("TPA", Main.tpaCache.contains(p.getUniqueId())));
                    inv.setItem(24, getItem2("TELL", Main.chatCache.contains(p.getUniqueId())));
                    inv.setItem(22, getItem3("GLOBAL", Main.globalCache.contains(p.getUniqueId())));
                    inv.setItem(31, getItem4("SONS", Main.soundCache.contains(p.getUniqueId())));

                    p.openInventory(inv);
                    return true;
        }
        return false;

    }

    @EventHandler
    public void aoClicar(InventoryClickEvent e) {
        Inventory inv = Bukkit.createInventory(null, 45, "§7Ativar/Desativar Funções");

        Player pe = (Player) e.getWhoClicked();

        if (e.getWhoClicked() instanceof Player && e.getInventory().getTitle().equals("§7Ativar/Desativar Funções")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null)
                return;
            if (!e.getCurrentItem().hasItemMeta())
                return;

        if (pe.hasPermission("toggle.re")) {
            if (e.getCurrentItem().equals(getItem("TPA", false))) {
                Main.tpaCache.add(pe.getUniqueId());
                e.getInventory().setItem(20, getItem("TPA", true));
                pe.playSound(pe.getLocation(), Sound.ANVIL_BREAK, 10, 1);
                pe.sendMessage("§aO recebimento de pedidos de TPA foi desativado");
                return;

            }

            if (e.getCurrentItem().equals(getItem("TPA", true))) {
                Main.tpaCache.remove(pe.getUniqueId());
                e.getInventory().setItem(20, getItem("TPA", false));
                pe.playSound(pe.getLocation(), Sound.ANVIL_USE, 10, 1);
                pe.sendMessage("§aO recebimento de pedidos de TPA foi ativado");
                return;

            }

            if (e.getCurrentItem().equals(getItem3("GLOBAL", false))) {
                Main.globalCache.add(pe.getUniqueId());
                e.getInventory().setItem(22, getItem3("GLOBAL", true));
                pe.playSound(pe.getLocation(), Sound.ANVIL_BREAK, 10, 1);
                pe.sendMessage("§aO recebimento de mensagens do GLOBAL foi desativado");
                return;

            }

            if (e.getCurrentItem().equals(getItem3("GLOBAL", true))) {
                Main.globalCache.remove(pe.getUniqueId());
                e.getInventory().setItem(22, getItem3("GLOBAL", false));
                pe.playSound(pe.getLocation(), Sound.ANVIL_USE, 10, 1);
                pe.sendMessage("§aO recebimento de mensagens do GLOBAL foi ativado");
                return;

            }
        } else {
            pe.sendMessage("§cExclusivo para §eVIPs§c. Adquira o seu em redeeterinity.com");

        }
            if (e.getCurrentItem().equals(getItem4("SONS", false))) {
                Main.soundCache.add(pe.getUniqueId());
                e.getInventory().setItem(31, getItem4("SONS", true));
                pe.playSound(pe.getLocation(), Sound.ANVIL_BREAK, 10, 1);
                pe.sendMessage("§aO recebimento de SONS por comandos foi desativado");
                return;

            }

            if (e.getCurrentItem().equals(getItem4("SONS", true))) {
                Main.soundCache.remove(pe.getUniqueId());
                e.getInventory().setItem(31, getItem4("SONS", false));
                pe.playSound(pe.getLocation(), Sound.ANVIL_USE, 10, 1);
                pe.sendMessage("§aO recebimento de SONS por comandos foi ativado");
                return;

            }


            if (e.getCurrentItem().equals(getItem2("TELL", false))) {
                Main.chatCache.add(pe.getUniqueId());
                e.getInventory().setItem(24, getItem2("TELL", true));
                pe.playSound(pe.getLocation(), Sound.ANVIL_BREAK, 10, 1);
                pe.sendMessage("§aO recebimento de mensagens pelo TELL foi desativado");
                return;

            }

            if (e.getCurrentItem().equals(getItem2("TELL", true))) {
                Main.chatCache.remove(pe.getUniqueId());
                e.getInventory().setItem(24, getItem2("TELL", false));
                pe.playSound(pe.getLocation(), Sound.ANVIL_USE, 10, 1);
                pe.sendMessage("§aO recebimento de mensagens pelo TELL foi ativado");
                return;

            }
        }
    }
    @EventHandler
    public void noTab(PlayerChatTabCompleteEvent e){
        if(e.getChatMessage().equals("/toggledownfall"))e.getTabCompletions().clear();
    }
}
