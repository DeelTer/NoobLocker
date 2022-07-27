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
import ru.deelter.nooblocker.NoobManager;
import ru.deelter.nooblocker.configs.LockerConfig;

public class PlayerBlocksListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlaceBedNether(@NotNull BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getWorld().getEnvironment() == World.Environment.NORMAL) return;
        if (!MaterialTags.BEDS.isTagged(block)) return;
        if (!LockerConfig.isEnablePlaceNetherBed()) return;

        Player player = event.getPlayer();
        if (NoobManager.hasFullAccess(player)) return;

        player.sendActionBar(LockerConfig.getWarnMessageAsComp());
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onExplodeNetherBed(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (block.getWorld().getEnvironment() == World.Environment.NORMAL) return;
        if (!MaterialTags.BEDS.isTagged(block)) return;
        if (!LockerConfig.isEnableExplodeNetherBed()) return;

        Player player = event.getPlayer();
        if (NoobManager.hasFullAccess(player)) return;

        Component warnMessage = LockerConfig.getWarnMessageAsComp();
        player.sendActionBar(warnMessage);
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlaceTnt(@NotNull BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.TNT) return;
        if (!LockerConfig.isEnablePlaceTnt()) return;

        Player player = event.getPlayer();
        if (NoobManager.hasFullAccess(player)) return;

        player.sendActionBar(LockerConfig.getWarnMessageAsComp());
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteractItems(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack usedItem = event.getItem();
        if (usedItem == null) return;

        boolean forbidden = switch (usedItem.getType()) {
            case FLINT_AND_STEEL -> LockerConfig.isEnableUseFlintAndSteel();
            case END_CRYSTAL -> LockerConfig.isEnablePlaceCrystal();
            case LAVA_BUCKET -> LockerConfig.isEnableUseLavaBucket();
            default -> false;
        };

        if (!forbidden) return;
        if (NoobManager.hasFullAccess(player)) return;

        player.sendActionBar(LockerConfig.getWarnMessageAsComp());
        event.setCancelled(true);
    }
}
