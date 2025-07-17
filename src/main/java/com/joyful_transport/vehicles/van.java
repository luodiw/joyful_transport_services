package com.joyful_transport.vehicles;

import com.joyful_transport.controls.CentralControlSystem;
import com.joyful_transport.services.fareCollector;

public class van implements vehicles {
	int licensePlateNo;
	int tripCount;
	fareCollector fc = new fareCollector();

	public van(Integer van_number) {
		licensePlateNo = van_number;
		this.tripCount = CentralControlSystem.returnTripCount(van_number);
	}

	@Override
	public int getTripCount() {
		return tripCount;
	}

	@Override
	public void resetTripCount() {
		this.tripCount = 0;
	}
}
