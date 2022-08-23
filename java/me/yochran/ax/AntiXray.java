package me.yochran.ax;

import me.yochran.ax.commands.AXCommand;
import me.yochran.ax.listeners.BlockBreakListener;
import me.yochran.ax.listeners.InventoryClickListener;
import me.yochran.ax.utils.Chat;
import me.yochran.ax.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class AntiXray extends JavaPlugin {

    // instance
    private static AntiXray instance;

    /**
     * Get the instance
     * @return - instance
     */
    public static AntiXray getInstance() { return instance; }

    // plugin manager
    private PluginManager manager;

    @Override
    public void onEnable() {
        // print loading message
        Bukkit.getConsoleSender().sendMessage("AntiXray v1.0.0 by Yochran is loading...");

        // set the instance
        instance = this;

        // set plugin manager
        manager = getServer().getPluginManager();

        // set config
        saveDefaultConfig();

        // load
        getCommand("ax").setExecutor(new AXCommand());
        listeners();

        // print loaded message
        Bukkit.getConsoleSender().sendMessage("AntiXray v1.0.0 by Yochran has successfully loaded.");
    }

    // objects
    public final Map<UUID, List<XMaterial>> players = new HashMap<>();

    // initialize listeners
    void listeners() {
        // for every listener
        Arrays.asList(
                new InventoryClickListener(),
                new BlockBreakListener()
        ).forEach(listener -> {
            // register
            manager.registerEvents(listener, this);
        });
    }
}
