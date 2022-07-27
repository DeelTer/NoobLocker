package ru.deelter.nooblocker.regions;

import org.bukkit.Location;

public class RestrictedArea {

	private final Location location;
	private final double radius;

	public RestrictedArea(Location location, double radius) {
		this.location = location;
		this.radius = radius;
	}

	public Location getLocation() {
		return location;
	}

	public double getRadius() {
		return radius;
	}
}
