package ru.deelter.nooblocker;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class LockerConfig {

    private final NoobLocker instance;
    private int noobTimeMinutes;
    private String warnMessage;
    private boolean enablePlaceTnt;
    private boolean enablePlaceCrystal;
    private boolean enablePlaceNetherBed;
    private boolean enableExplodeNetherBed;
    private boolean enableKillVillagers;
    private boolean enableExplodeCrystal;
    private boolean enableUseFlintAndSteel;
    private boolean enableUseLavaBucket;
    private boolean enableDamagePlayers;

    public LockerConfig(@NotNull NoobLocker instance) {
        this.instance = instance;
        this.load();
    }

    private void load() {
        FileConfiguration config = this.instance.getConfig();
        int noobTimeMinutes = config.getInt("noob-time.minutes");
        int noobTimeDays = config.getInt("noob-time.days");
        this.noobTimeMinutes = (int) (noobTimeMinutes + TimeUnit.DAYS.toMinutes(noobTimeDays));
        this.warnMessage = config.getString("messages.warn");

        this.enablePlaceTnt = config.getBoolean("actions.place-tnt");
        this.enablePlaceCrystal = config.getBoolean("actions.place-crystal");
        this.enablePlaceNetherBed = config.getBoolean("actions.place-nether-bed");
        this.enableExplodeNetherBed = config.getBoolean("actions.explode-nether_bed");
        this.enableKillVillagers = config.getBoolean("actions.kill-villagers");
        this.enableExplodeCrystal = config.getBoolean("actions.explode-crystal");
        this.enableUseFlintAndSteel = config.getBoolean("actions.use-flind-and-steel");
        this.enableUseLavaBucket = config.getBoolean("actions.use-lava-bucket");
        this.enableDamagePlayers = config.getBoolean("actions.damage-players");
    }

    public void reload() {
        instance.reloadConfig();
        this.load();
    }

    public int getNeedMinutes() {
        return noobTimeMinutes;
    }

    public String getWarnMessage() {
        return warnMessage;
    }

    @Contract(value = " -> new", pure = true)
    public @NotNull Component getWarnMessageAsComp() {
        return Component.text(warnMessage);
    }

    public boolean isEnablePlaceNetherBed() {
        return enablePlaceNetherBed;
    }

    public boolean isEnableExplodeNetherBed() {
        return enableExplodeNetherBed;
    }

    public boolean isEnableKillVillagers() {
        return enableKillVillagers;
    }

    public boolean isEnableExplodeCrystal() {
        return enableExplodeCrystal;
    }

    public boolean isEnablePlaceTnt() {
        return enablePlaceTnt;
    }

    public boolean isEnableUseFlintAndSteel() {
        return enableUseFlintAndSteel;
    }

    public boolean isEnableUseLavaBucket() {
        return enableUseLavaBucket;
    }

    public boolean isEnablePlaceCrystal() {
        return enablePlaceCrystal;
    }

    public boolean isEnableDamagePlayers() {
        return enableDamagePlayers;
    }

    public FileConfiguration getBukkitConfig() {
        return instance.getConfig();
    }
}
