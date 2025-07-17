package com.joyful_transport.services;

import java.util.HashMap;
import com.joyful_transport.defs.commuters;
import com.joyful_transport.defs.Stops;

public class request {
	static HashMap<commuters, Stops> commuterList = new HashMap<commuters,Stops>();
	static HashMap<Stops, Integer> stopsList = new HashMap<Stops, Integer>();

	public request(commuters c1, Stops s1) {
		commuterList.put(c1, s1);
		if (stopsList.get(s1) != null) {
			stopsList.put(s1, stopsList.get(s1) + 1);
		} else {
			stopsList.put(s1, 1);
		}
	}

	public static int getRequestCount(Stops s1) {
		return stopsList.get(s1);
	}
}
