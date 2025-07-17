package com.joyful_transport.vehicles;

import com.joyful_transport.defs.Route;
import com.joyful_transport.defs.Stops;
import com.joyful_transport.vehicles.vehicles;
import com.joyful_transport.services.fareCollector;
import com.joyful_transport.controls.centralControlSystem;

public class bus implements vehicles {
    public Route route = null;
	int busTime = 0;
	Stops currentLocation = centralControlSystem.stops[8];

	public bus(Integer bus_number) {
		licensePlateNo = bus_number;
		this.tripCount = centralControlSystem.returnTripCount(bus_number);
	}

	public void updateRoute(Route r) {
		this.route = r;
	}

	//@Ensures({tripCount=old(tripCount)+1})
	public void updateTripCount() {
		tripCount += 1;

	}

	public void updateLocation(Stops stops) {
		currentLocation = stops;
	}

	public Stops returnLocation() {
		return (currentLocation);
	}

	@Override
	public int getTripCount() {
		return this.tripCount;
	}

	@Override
	public void resetTripCount() {
		this.tripCount = 0;
	}

	int licensePlateNo;
	int tripCount;
	fareCollector fc = new fareCollector();

}
