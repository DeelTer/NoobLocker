package ru.deelter.nooblocker.regions;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.deelter.nooblocker.NoobLocker;

import java.util.HashSet;
import java.util.Set;

public class RestrictedAreaManager {

	private static final Set<RestrictedArea> AREAS = new HashSet<>();

	public static void load(@NotNull NoobLocker instance) {
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
			Location location = areaSection.getLocation("location");
			double radius = areaSection.getDouble("radius");
			RestrictedArea area = new RestrictedArea(location, radius);
			AREAS.add(area);
		}
	}

	public static @Nullable RestrictedArea getAreaIn(Location location) {
		for (RestrictedArea area : AREAS) {
			Location areaLocation = area.getLocation();
			if (areaLocation.getWorld().equals(location.getWorld())) continue;
			if (areaLocation.distance(location) <= area.getRadius()) return area;
		}
		return null;
	}
}
