package com.joyful_transport.services;

import com.joyful_transport.defs.commuters;
import com.joyful_transport.vehicles.bus;

public class fareCollector {
	commuters c;
	public int tripFare;

	public void requestToBoardVehicle(commuters commuters, bus b) {
		c = commuters;
		c.boardedAt = b.currentLocation;
	}

	public void requestToDepartVehicle(commuters commuters, bus b) {
		c.departedAt = b.currentLocation;
		tripFare += (b.route.routes.indexOf(c.departedAt) - b.route.routes.indexOf(c.boardedAt)) * 2;
	}
}
