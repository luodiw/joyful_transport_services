package com.joyful_transport.defs;

public class Schedule {
	public Schedule(Route route, double start,String type) {
		this.route = route;
		this.starttime = start;
		this.type=type;
	}

	public Route route;
	public double starttime;
	public String type;
}
