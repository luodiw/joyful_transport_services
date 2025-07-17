package com.joyful_transport.defs;

import com.joyful_transport.controls.CentralControlSystem;
import com.joyful_transport.vehicles.bus;

public class startTrip implements Runnable {
	bus b1;
	Driver d1;
	Schedule s1;

	//@Requires({"b1!=null","d1!=null","s1!=null"})
	//@Ensures({"b1.route == s1.route"})
	public startTrip(Schedule s, bus b, Driver d) {
		b1 = b;
		d1 = d;
		s1 = s;
		b1.updateRoute(s1.route);
	}
	
	@Override
	public void run() {
		System.out.println(
				"Route Size:" + s1.route.routes.size() + "  Bus operates From " + s1.route.routes.get(0).name + " to " + s1.route.routes.get(1).name);
		for (int i = 0; i < s1.route.routes.size(); i++) {
			System.out.println("Bus: " + b1.licensePlateNo + " is driven by: " + d1.drivername + " at stop: "
					+ b1.returnLocation().name + " next stop: " + (i+1));
			b1.updateLocation(s1.route.routes.get(i));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(s1.type.equals("Additional")) {
			CentralControlSystem.RemoveBusSchedule(s1);
		}
		b1.updateTripCount();
		CentralControlSystem.fareCollected += b1.fc.tripFare;
		CentralControlSystem.addVehicleToAvailableList(b1.licensePlateNo);
		CentralControlSystem.addDriverToAvailableList(d1);
	}
}
