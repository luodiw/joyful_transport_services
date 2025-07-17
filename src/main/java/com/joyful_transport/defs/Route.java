package com.joyful_transport.defs;

import java.util.ArrayList;

public class Route {
	public String name;
	public ArrayList<Stops> routes = new ArrayList<Stops>();
	
	//@Requires({"Stops.size()>0"}
	public Route(Stops[] s, String name) {
		this.name = name;
		for (int i = 0; i < s.length; i++) {
			this.routes.add(s[i]);
		}
	}
}
