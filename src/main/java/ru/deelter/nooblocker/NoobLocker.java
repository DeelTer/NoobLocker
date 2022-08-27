package ru.deelter.nooblocker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.deelter.nooblocker.commands.NoobLockerCommand;
import ru.deelter.nooblocker.listeners.PlayerBlocksListener;
import ru.deelter.nooblocker.listeners.PlayerEntityListener;

public final class NoobLocker extends JavaPlugin {

    private static NoobLocker instance;
    private static LockerConfig lockerConfig;

    public static NoobLocker getInstance() {
        return instance;
    }

    public static LockerConfig getLockerConfig() {
        return lockerConfig;
    }

    @Override
    public void onLoad() {
        instance = this;
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        lockerConfig = new LockerConfig(this);

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
