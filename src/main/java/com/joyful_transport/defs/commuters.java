package com.joyful_transport.defs;

class commuters {
	int id;
	public Stops boardedAt = null;
	public Stops departedAt = null;
	String commuterDisplay;

	public commuters(int id) {
		this.id = id;
	}

	public void registerCommuter() {
		centralControlSystem.registerCommuter(this);
	}

	public void retrieveRouteMap(String sourceStop, String destStop) {
		commuterDisplay = centralControlSystem.retrieveRouteMap(sourceStop, destStop);
		this.displayNotifications(commuterDisplay);
	}

	public void retrieveFare(String sourceStop, String destStop) {
		commuterDisplay = centralControlSystem.retrieveFare(sourceStop, destStop);
		this.displayNotifications(commuterDisplay);
	}

	public void retrieveSchedule(String sourceStop) {
		commuterDisplay = centralControlSystem.retrieveSchedule(sourceStop);
		this.displayNotifications(commuterDisplay);
	}

	public void retrieveRealTimeLocation() {
		commuterDisplay = centralControlSystem.retrieveRealTimeLocations();
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
	centralControlSystem.requestVan(this, s);
	
	}
}
