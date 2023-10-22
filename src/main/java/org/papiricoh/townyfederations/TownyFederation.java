package org.papiricoh.townyfederations;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.papiricoh.townyfederations.object.Federation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class TownyFederation extends JavaPlugin {
    public static TownyFederation instance;
    PluginManager pluginManager = Bukkit.getServer().getPluginManager();
    private boolean isEventWarEnabled = false;
    private Map<UUID, Federation> federations;

    @Override
    public void onEnable() {
        this.instance = this;
        this.federations = new HashMap<>();
        if (pluginManager.getPlugin("EventWar") != null && pluginManager.getPlugin("EventWar").isEnabled()) {
            this.isEventWarEnabled = true;
        }


    }

    public Federation getFederation(String uuid) {
        return this.federations.get(UUID.fromString(uuid));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean isEventWarEnabled() {
        return isEventWarEnabled;
    }

    public static TownyFederation getInstance() {
        return instance;
    }
}
