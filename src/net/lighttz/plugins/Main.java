package net.lighttz.plugins;

import net.lighttz.plugins.chats.*;
import net.lighttz.plugins.chats.ChatGlobal;
import net.lighttz.plugins.chats.ChatTell;
import net.lighttz.plugins.join.JoinMensagem;
import net.lighttz.plugins.join.Tags;
import net.lighttz.plugins.kits.Kits;
import net.lighttz.plugins.programutils.LConfig;
//import net.lighttz.plugins.staff.Punir;
import net.lighttz.plugins.teleport.TP;
import net.lighttz.plugins.teleport.TPA;
import net.lighttz.plugins.teleport.TPhere;
import net.lighttz.plugins.vipsutils.AnunciarVIP;
import net.lighttz.plugins.vipsutils.Toggle;
import org.bukkit.Bukkit;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    public static  Main instance;

    public static ArrayList<UUID> tpaCache;
    public static ArrayList<UUID> chatCache;
    public static ArrayList<UUID> globalCache;
    public static ArrayList<UUID> soundCache;

    public void onEnable(){

        instance = this;
        tpaCache = new ArrayList<>();
        chatCache = new ArrayList<>();
        globalCache = new ArrayList<>();
        soundCache = new ArrayList<>();




        Bukkit.getConsoleSender().sendMessage("§a[LSystem] iniciado, desenvolvido por ©ILighttz#0002 ");

        Bukkit.getPluginManager().registerEvents(new JoinMensagem (), this);

        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);


        getCommand("g").setExecutor(new ChatGlobal ());

        getCommand("anunciar").setExecutor(new AnunciarVIP ());

        getCommand("tell").setExecutor(new ChatTell ());

        getCommand("tpa").setExecutor(new TPA ());

        getCommand("tphere").setExecutor(new TPhere ());

        getCommand("tp").setExecutor(new TP ());

        getCommand("kits").setExecutor(new Kits ());

        getCommand("clearchat").setExecutor(new ClearChat());

        getCommand("chat").setExecutor(new DesativarChat());

        getCommand("s").setExecutor(new ChatStaff());

        getCommand("toggle").setExecutor(new Toggle());

        //getCommand("punir").setExecutor(new Punir());

        Bukkit.getPluginManager().registerEvents(new Kits (), this);

        Bukkit.getPluginManager().registerEvents(new Toggle (), this);

        Bukkit.getPluginManager().registerEvents(new ChatLocal(), this);

        //Bukkit.getPluginManager().registerEvents(new Punir(), this);

        Bukkit.getPluginManager().registerEvents(new Tags(), this);

    }



    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage("§c[LSystem] Desativando, aguarde, criado por ©ILighttz#0002");
    }

}

