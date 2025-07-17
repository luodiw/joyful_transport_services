package com.joyful_transport.defs

// needs to import central control system

class Driver {
	public Driver(String name) {
		this.drivername = name;
	}
	String drivername;
	public static void PlyadditionalBus(Route request, double time)
	{
		centralControlSystem.AddScheduleForOverCrowdRequest(request,time);
	}
}
