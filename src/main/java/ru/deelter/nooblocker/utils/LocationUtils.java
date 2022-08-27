package ru.deelter.nooblocker.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class LocationUtils {

    @NotNull
    public static Location fromString(@NotNull String str, @NotNull String splitSymbol) {
        String[] args = str.replace(" ", "").split(splitSymbol);
        if (args.length != 4) throw new RuntimeException("Missing arguments");

        World world = Bukkit.getWorld(args[0]);
        if (world == null) throw new RuntimeException("World " + world + " is null!");
        return new Location(world, Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
    }
}
