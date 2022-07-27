package ru.deelter.nooblocker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.deelter.nooblocker.commands.NoobLockerCommand;
import ru.deelter.nooblocker.configs.LockerConfig;
import ru.deelter.nooblocker.listeners.PlayerBlocksListener;
import ru.deelter.nooblocker.listeners.PlayerEntityListener;

public final class NoobLocker extends JavaPlugin {

    private static NoobLocker instance;

    public static NoobLocker getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        saveDefaultConfig();
        LockerConfig.load(this);
    }

    @Override
    public void onEnable() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new PlayerBlocksListener(), this);
        manager.registerEvents(new PlayerEntityListener(), this);
        NoobLockerCommand.setup();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
