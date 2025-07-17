package com.joyful_transport.defs;

import com.joyful_transport.controls.CentralControlSystem;

public class Driver {
	public Driver(String name) {
		this.drivername = name;
	}
	public String drivername;
	public static void PlyadditionalBus(Route request, double time)
	{
		CentralControlSystem.AddScheduleForOverCrowdRequest(request,time);
	}
}
