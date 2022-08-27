package ru.deelter.nooblocker.listeners;

import com.destroystokyo.paper.MaterialTags;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.deelter.nooblocker.LockerConfig;
import ru.deelter.nooblocker.NoobLocker;
import ru.deelter.nooblocker.managers.NoobManager;

public class PlayerBlocksListener implements Listener {

    private static final LockerConfig CONFIG = NoobLocker.getLockerConfig();

    @EventHandler(ignoreCancelled = true)
    public void onPlaceBedNether(@NotNull BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getWorld().getEnvironment() == World.Environment.NORMAL) return;
        if (!MaterialTags.BEDS.isTagged(block)) return;
        if (!CONFIG.isEnablePlaceNetherBed()) return;

        Player player = event.getPlayer();
        if (NoobManager.hasFullAccess(player)) return;

        player.sendActionBar(CONFIG.getWarnMessageAsComp());
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onExplodeNetherBed(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (block.getWorld().getEnvironment() == World.Environment.NORMAL) return;
        if (!MaterialTags.BEDS.isTagged(block)) return;
        if (!CONFIG.isEnableExplodeNetherBed()) return;

        Player player = event.getPlayer();
        if (NoobManager.hasFullAccess(player)) return;

        Component warnMessage = CONFIG.getWarnMessageAsComp();
        player.sendActionBar(warnMessage);
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlaceTnt(@NotNull BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.TNT) return;
        if (!CONFIG.isEnablePlaceTnt()) return;

        Player player = event.getPlayer();
        if (NoobManager.hasFullAccess(player)) return;

        player.sendActionBar(CONFIG.getWarnMessageAsComp());
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteractItems(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack usedItem = event.getItem();
        if (usedItem == null) return;

        boolean forbidden = switch (usedItem.getType()) {
            case FLINT_AND_STEEL -> CONFIG.isEnableUseFlintAndSteel();
            case END_CRYSTAL -> CONFIG.isEnablePlaceCrystal();
            case LAVA_BUCKET -> CONFIG.isEnableUseLavaBucket();
            default -> false;
        };

        if (!forbidden) return;
        if (NoobManager.hasFullAccess(player)) return;

        player.sendActionBar(CONFIG.getWarnMessageAsComp());
        event.setCancelled(true);
    }
}
