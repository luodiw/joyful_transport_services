package com.joyful_transport.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import com.joyful_transport.defs.commuters;
import com.joyful_transport.defs.Stops;

public class vanRequestService {
	int threshold = 2;
	ArrayList<request> requests = new ArrayList<request>(); 

	public void addRequest(commuters commuters, Stops s1) {
		requests.add(new request(commuters, s1));
		if(request.getRequestCount(s1) >= threshold) {
			Set<commuters> commuters1 = request.commuterList.keySet();
			Iterator<commuters> iter = commuters1.iterator();
			while (iter.hasNext()) {
				iter.next().displayNotifications("Van is being deployed for stop: " + s1.name);
			}
			request.stopsList.remove(s1);
			request.commuterList.remove(commuters, s1);
		}
	}
}
