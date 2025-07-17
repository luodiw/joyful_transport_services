package com.joyful_transport.defs;

import com.joyful_transport.controls.CentralControlSystem;
import com.joyful_transport.vehicles.bus;

public class commuters {
	int id;
	public Stops boardedAt = null;
	public Stops departedAt = null;
	String commuterDisplay;

	public commuters(int id) {
		this.id = id;
	}

	public void registerCommuter() {
		CentralControlSystem.registerCommuter(this);
	}

	public void retrieveRouteMap(String sourceStop, String destStop) {
		commuterDisplay = CentralControlSystem.retrieveRouteMap(sourceStop, destStop);
		this.displayNotifications(commuterDisplay);
	}

	public void retrieveFare(String sourceStop, String destStop) {
		commuterDisplay = CentralControlSystem.retrieveFare(sourceStop, destStop);
		this.displayNotifications(commuterDisplay);
	}

	public void retrieveSchedule(String sourceStop) {
		commuterDisplay = CentralControlSystem.retrieveSchedule(sourceStop);
		this.displayNotifications(commuterDisplay);
	}

	public void retrieveRealTimeLocation() {
		commuterDisplay = CentralControlSystem.retrieveRealTimeLocations();
		this.displayNotifications(commuterDisplay);
	}

	public void requestToBoardVehicle(bus b) {
		b.fc.requestToBoardVehicle(this, b);
	}

	public void requestToDepartVehicle(bus b) {
		b.fc.requestToDepartVehicle(this, b);
	}

	public void displayNotifications(String commuterDisplay) {
		System.out.println(commuterDisplay);
	}
	
	public void requestVan(String s) {
	CentralControlSystem.requestVan(this, s);
	
	}
}
