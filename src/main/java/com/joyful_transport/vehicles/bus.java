package com.joyful_transport.vehicles;

import com.joyful_transport.defs.Route;
import com.joyful_transport.defs.Stops;
import com.joyful_transport.controls.CentralControlSystem;
import com.joyful_transport.services.fareCollector;

//@Invariant({"licensePlateNo!=null","currentLocation!=null"})
public class bus implements vehicles {
	public Route route = null;
	int busTime = 0;
	public Stops currentLocation = CentralControlSystem.stops[8];
	public int licensePlateNo;
	int tripCount;
	public fareCollector fc = new fareCollector();

	public bus(Integer bus_number) {
		licensePlateNo = bus_number;
		this.tripCount = CentralControlSystem.returnTripCount(bus_number);
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
}
