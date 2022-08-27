package ru.deelter.nooblocker.managers;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.deelter.nooblocker.NoobLocker;
import ru.deelter.nooblocker.utils.LocationUtils;
import ru.deelter.nooblocker.utils.RestrictedArea;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RestrictedAreaManager {

	private static final Set<RestrictedArea> AREAS = new HashSet<>();

	public static void load() {
		NoobLocker instance = NoobLocker.getInstance();
		FileConfiguration config = instance.getConfig();
		ConfigurationSection areasSection = config.getConfigurationSection("restricted-areas");
		if (areasSection == null) {
			instance.getLogger().warning("No restricted area section in config.yml");
			return;
		}
		for (String path : areasSection.getKeys(false)) {
			ConfigurationSection areaSection = areasSection.getConfigurationSection(path);
			if (areaSection == null) {
				instance.getLogger().warning("Invalid area");
				continue;
			}
			Location location = LocationUtils.fromString(Objects.requireNonNull(areaSection.getString("location")), ",");
			double radius = areaSection.getDouble("radius");
			RestrictedArea area = new RestrictedArea(location, radius);
			AREAS.add(area);
		}
	}

	public static void reload() {
		AREAS.clear();
		load();
	}

	public static @Nullable RestrictedArea getAreaIn(@NotNull Location location) {
		for (RestrictedArea area : AREAS) {
			Location areaLocation = area.getLocation();
			if (areaLocation.getWorld().equals(location.getWorld())) continue;
			if (areaLocation.distance(location) <= area.getRadius()) return area;
		}
		return null;
	}

	public static boolean isInRestrictedArea(@NotNull Player player) {
		return getAreaIn(player.getLocation()) != null;
	}
}
