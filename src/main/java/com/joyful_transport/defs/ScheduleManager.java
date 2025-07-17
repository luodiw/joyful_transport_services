package com.joyful_transport.defs;

import java.util.*;

import com.joyful_transport.controls.CentralControlSystem;
import com.joyful_transport.vehicles.bus;

public class ScheduleManager implements Runnable {
	Time time;
	static Schedule[] schedule;
	CentralControlSystem ccs;

	//@Requires({"time>=0", "time<=120", "s.length>=0"})
	public ScheduleManager(Schedule[] s, Time t) {
		schedule = s;
		time = t;
	}

	public static void UpdateSchedule(Schedule[] s) {
		schedule = s;
	}
	
	public void run() {
		
		System.out.println("Run schedule");
		bus b;
		Driver d;
		ArrayList<Thread> trips = new ArrayList<Thread>();
		while (true) {
			for (int i = 0; i < schedule.length; i++) {
				if (schedule[i].starttime == time.getHour()) {
					System.out.println("Scheduled Trip For Bus :" + (i+1));
					b = CentralControlSystem.getBus();
					if (b == null) {
						System.out.println("No buses available!");
						continue;
					}
					d = CentralControlSystem.getDriver();
					if (d == null) {
						System.out.println("No drivers available!");
						continue;
					}
					startTrip trip = new startTrip(schedule[i], b, d);
					trips.add(new Thread (trip, "trip"));
					System.out.println("Starting trip");
					trips.get(trips.size()-1).start();
			}
				
		}
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
