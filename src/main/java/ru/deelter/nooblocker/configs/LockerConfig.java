package ru.deelter.nooblocker.configs;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.deelter.nooblocker.NoobLocker;

import java.util.concurrent.TimeUnit;

public class LockerConfig {

    private static int noobTimeMinutes;
    private static String warnMessage;
    private static boolean enablePlaceTnt;
    private static boolean enablePlaceCrystal;
    private static boolean enablePlaceNetherBed;
    private static boolean enableExplodeNetherBed;
    private static boolean enableKillVillagers;
    private static boolean enableExplodeCrystal;
    private static boolean enableUseFlintAndSteel;
    private static boolean enableUseLavaBucket;
    private static boolean enableDamagePlayers;

    public static void load(@NotNull NoobLocker instance) {
        FileConfiguration config = instance.getConfig();
        int noobTimeMinutes = config.getInt("noob-time.minutes");
        int noobTimeDays = config.getInt("noob-time.days");
        LockerConfig.noobTimeMinutes = (int) (noobTimeMinutes + TimeUnit.DAYS.toMinutes(noobTimeDays));
        warnMessage = config.getString("messages.warn");

        enablePlaceTnt = config.getBoolean("actions.place-tnt");
        enablePlaceCrystal = config.getBoolean("actions.place-crystal");
        enablePlaceNetherBed = config.getBoolean("actions.place-nether-bed");
        enableExplodeNetherBed = config.getBoolean("actions.explode-nether_bed");
        enableKillVillagers = config.getBoolean("actions.kill-villagers");
        enableExplodeCrystal = config.getBoolean("actions.explode-crystal");
        enableUseFlintAndSteel = config.getBoolean("actions.use-flind-and-steel");
        enableUseLavaBucket = config.getBoolean("actions.use-lava-bucket");
        enableDamagePlayers = config.getBoolean("actions.damage-players");
    }

    public static void reload(@NotNull NoobLocker instance) {
        instance.reloadConfig();
        load();
    }

    public static int getNeedMinutes() {
        return noobTimeMinutes;
    }

    public static String getWarnMessage() {
        return warnMessage;
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Component getWarnMessageAsComp() {
        return Component.text(warnMessage);
    }

    public static boolean isEnablePlaceNetherBed() {
        return enablePlaceNetherBed;
    }

    public static boolean isEnableExplodeNetherBed() {
        return enableExplodeNetherBed;
    }

    public static boolean isEnableKillVillagers() {
        return enableKillVillagers;
    }

    public static boolean isEnableExplodeCrystal() {
        return enableExplodeCrystal;
    }

    public static boolean isEnablePlaceTnt() {
        return enablePlaceTnt;
    }

    public static boolean isEnableUseFlintAndSteel() {
        return enableUseFlintAndSteel;
    }

    public static boolean isEnableUseLavaBucket() {
        return enableUseLavaBucket;
    }

    public static boolean isEnablePlaceCrystal() {
        return enablePlaceCrystal;
    }

    public static boolean isEnableDamagePlayers() {
        return enableDamagePlayers;
    }
}
