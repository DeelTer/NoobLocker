package ru.deelter.nooblocker.listeners;

import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;
import ru.deelter.nooblocker.NoobLocker;
import ru.deelter.nooblocker.managers.NoobManager;
import ru.deelter.nooblocker.LockerConfig;

public class PlayerEntityListener implements Listener {
    
    private static final LockerConfig CONFIG = NoobLocker.getLockerConfig();

    @EventHandler
    public void onDamageVillagers(@NotNull EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Villager)) return;
        if (!(event.getDamager() instanceof Player player)) return;
        if (!CONFIG.isEnableKillVillagers()) return;
        if (NoobManager.hasFullAccess(player)) return;

        player.sendActionBar(CONFIG.getWarnMessageAsComp());
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamagePlayers(@NotNull EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player player)) return;
        if (!CONFIG.isEnableDamagePlayers()) return;

        if (NoobManager.hasFullAccess(player)) return;

        player.sendActionBar(CONFIG.getWarnMessageAsComp());
        event.setCancelled(true);
    }

    @EventHandler
    public void onKillCrystal(@NotNull EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof EnderCrystal)) return;
        if (!(event.getDamager() instanceof Player player)) return;
        if (!CONFIG.isEnableExplodeCrystal()) return;

        if (NoobManager.hasFullAccess(player)) return;

        player.sendActionBar(CONFIG.getWarnMessageAsComp());
        event.setCancelled(true);
    }
}
