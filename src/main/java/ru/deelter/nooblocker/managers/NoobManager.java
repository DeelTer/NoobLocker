package ru.deelter.nooblocker.managers;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.deelter.nooblocker.NoobLocker;

public class NoobManager {

    public static boolean hasFullAccess(@NotNull Player player) {
        if (player.hasPermission("nooblocker.bypass")) return true;
        if (player.hasPermission("nooblocker.griefer")) return false;
        int playedMinutes = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 60;
        int needPlay = NoobLocker.getLockerConfig().getNeedMinutes();
        long difference = playedMinutes - needPlay;
        if (difference > 0) return true;
        return !RestrictedAreaManager.isInRestrictedArea(player);
    }

}
