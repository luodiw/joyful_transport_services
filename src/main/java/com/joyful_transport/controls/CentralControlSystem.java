package com.joyful_transport.controls;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.joyful_transport.defs.commuters;
import com.joyful_transport.defs.Route;
import com.joyful_transport.defs.Stops;
import com.joyful_transport.defs.Time;
import com.joyful_transport.defs.Driver;
import com.joyful_transport.defs.Schedule;
import com.joyful_transport.defs.ScheduleManager;
import com.joyful_transport.vehicles.bus;
import com.joyful_transport.employees.maintenanceStaff;
import com.joyful_transport.services.vanRequestService;

public class CentralControlSystem {
	public static int fareCollected;
	public static bus[] buses;
	public static Stops[] stops = new Stops[8];
	static commuters[] commuter = new commuters[5];
	static Integer number_of_routes = 4;
	static Route[] route = new Route[number_of_routes];
	static Integer number_of_vehicle = 5;
	static Integer number_of_Drivers = 5;
	static Time time = new Time();
	static int numberOfSchedules=16;
	static vanRequestService vanRequestService = new vanRequestService();

	static Driver[] drivers = new Driver[number_of_Drivers];
	static Set<Driver> availableDrivers;
	static Schedule[] schedule = new Schedule[numberOfSchedules];
	static int[] tripcount = { 13, 2, 6, 33, 11 };

	static Set<Integer> availableBuses;
	static ArrayList<commuters> commuter_database = new ArrayList<commuters>();

	public CentralControlSystem() throws InterruptedException {
		System.out.println("bleh");
		availableDrivers = new HashSet<>();

		drivers[0] = new Driver("Tom");
		drivers[1] = new Driver("Ding");
		drivers[2] = new Driver("Mat");
		drivers[3] = new Driver("Bryan");
		drivers[4] = new Driver("Kelly");

		availableDrivers.add(drivers[0]);
		availableDrivers.add(drivers[1]);
		availableDrivers.add(drivers[2]);
		availableDrivers.add(drivers[3]);
		availableDrivers.add(drivers[4]);

		commuter[0] = new commuters(50208400);
		commuter[1] = new commuters(50208401);
		commuter[2] = new commuters(50208402);
		commuter[3] = new commuters(50208403);
		commuter[4] = new commuters(50208404);

		commuter_database.add(commuter[0]);
		commuter_database.add(commuter[1]);
		commuter_database.add(commuter[2]);
		commuter_database.add(commuter[3]);
		commuter_database.add(commuter[4]);

		Integer number_of_stops = 9;
		stops = new Stops[number_of_stops];

		stops[0] = new Stops("Ellicott Tunnel");
		stops[1] = new Stops("Lee Loop");
		stops[2] = new Stops("Governors");
		stops[3] = new Stops("Flint Loop");
		stops[4] = new Stops("Centre For Tomorrow");
		stops[5] = new Stops("Maynard");
		stops[6] = new Stops("Goodyear");
		stops[7] = new Stops("South Campus");
		stops[8] = new Stops("Garage");

		route[0] = new Route(new Stops[] { stops[3], stops[6], stops[7] }, "Yellow Line");
		route[1] = new Route(new Stops[] { stops[7], stops[6], stops[3] }, "Green Line");
		route[2] = new Route(new Stops[] { stops[0], stops[1], stops[2], stops[3], stops[4], stops[5], stops[7] },
				"South Campus");
		route[3] = new Route(new Stops[] { stops[7], stops[5], stops[4], stops[3], stops[2], stops[1], stops[0] },
				"North Campus");

		schedule[0] = new Schedule(route[0], 1,"normal");
		schedule[1] = new Schedule(route[1], 8,"normal");
		schedule[2] = new Schedule(route[0], 11,"normal");
		schedule[3] = new Schedule(route[1], 13,"normal");
		schedule[4] = new Schedule(route[0], 15,"normal");
		schedule[5] = new Schedule(route[1], 17,"normal");
		schedule[6] = new Schedule(route[0], 19,"normal");

		schedule[7] = new Schedule(route[2], 8,"normal");
		schedule[8] = new Schedule(route[3], 10,"normal");
		schedule[9] = new Schedule(route[2], 12,"normal");
		schedule[10] = new Schedule(route[3], 14,"normal");
		schedule[11] = new Schedule(route[2], 16,"normal");
		schedule[12] = new Schedule(route[3], 18,"normal");
		schedule[13] = new Schedule(route[2], 20,"normal");
		schedule[14] = new Schedule(route[3], 22,"normal");

		Integer number_of_buses = 5;
		buses = new bus[number_of_buses];
		availableBuses = new HashSet<>();
		for (int i = 0; i < number_of_buses; i++) {
			buses[i] = new bus(i);
			availableBuses.add(i);
		}

		drivers[0].PlyadditionalBus(route[0], 1.0);
		Thread timeThread = new Thread(time, "timeThread");
		timeThread.start(); 
		ScheduleManager ScheduleManager = new ScheduleManager(schedule, time); 
		Thread ScheduleManagerThread = new Thread(ScheduleManager,"ScheduleManagerThread");
		ScheduleManagerThread.start();
	}

	public static void requestVan(commuters commuters, String s) {
		Stops s1=null;
		for (int i=0; i<stops.length;i++) {
			if(stops[i].name.equals(s)) {
				s1=stops[i];
			}
		}
		vanRequestService.addRequest(commuters, s1);
	}
		
	public static String retrieveSchedule(String sourceStop) {
		String commuterDisplay = "";
		for (int i = 0; i < schedule.length; i++) {
			for (int j = 0; j < schedule[i].route.routes.size(); j++) {
				if (schedule[i].route.routes.get(j).name.equals(sourceStop))
					commuterDisplay = commuterDisplay + " Bus for route" + schedule[i].route.name + " will arrive on "
							+ sourceStop + " at " + (schedule[i].starttime + (j * 2) / 10) + "\n";
			}
		}
		return commuterDisplay;
	}
	
	public static Route returnRoute(int number) {
		return route[number];
	}
	
	public static void AddScheduleForOverCrowdRequest(Route request, double time) {
		schedule[15] = new Schedule(request, time,"Additional");
		bus b;
		Driver d;
		
		b = getBus();
		d = getDriver();
		if (b==null || d == null) {
			System.out.println("No Available Resources");
			return;
		}

		ScheduleManager.UpdateSchedule(schedule);
		String commuterDisplay = "New buses at route " + schedule[15].route.routes.get(0).name + " at "+
				schedule[15].starttime;
		
		//UC7
		for(int i=0;i<5;i++) {
			commuter[i].displayNotifications(commuterDisplay);
		}
	}

	public static void RemoveBusSchedule(Schedule to_delete) {
		to_delete=null;
		ScheduleManager.UpdateSchedule(schedule);
	}
	
	public static String retrieveRealTimeLocations() {
		String commuterDisplay = "";
		for (int i = 0; i < buses.length; i++) {
			commuterDisplay = commuterDisplay + " Bus number: " + buses[i].licensePlateNo + " is at "
					+ buses[i].currentLocation.name + "\n";
		}
		return commuterDisplay;
	}

	public static Driver getDriver() {
		if (availableDrivers != null) {
			Iterator<Driver> iter = availableDrivers.iterator();
			if (iter.hasNext()) {
				Driver driver = iter.next();
				removeDriverFromAvailableList(driver);
				return driver;
			}
		}
		return null;
	}

	public static void removeDriverFromAvailableList(Driver d) {
		if (availableDrivers != null)
			availableDrivers.remove(d);
	}

	public static void addDriverToAvailableList(Driver d) {
		availableDrivers.add(d);
	}

	public static bus getBus() {
		if (availableBuses != null) {
			Iterator<Integer> iter = availableBuses.iterator();
			if (iter.hasNext()) {
				int vehicleNumber = iter.next();
				removeVehicleFromAvailableList(vehicleNumber);
				return buses[vehicleNumber];
			}
		}
		return null;
	}

	public static void displayFareCollected() {
		System.out.println("Central Control System: The total fare collected for the day is: " + fareCollected);
		
		maintenanceStaff employee = new maintenanceStaff();
		employee.checkMaintenanceStatus();
	}
	
	public static int returnTripCount(Integer vehicle_number) {
		return (tripcount[vehicle_number]);
	}

	public static bus[] returnBusObject() {
		return buses;
	}

	public static boolean removeVehicleFromAvailableList(int vehicle_number) {
		if (availableBuses != null) {
			availableBuses.remove(vehicle_number);
		}
		return true;
	}

	public static boolean addVehicleToAvailableList(int vehicle_number) {
		availableBuses.add(vehicle_number);
		return true;
	}

	public static String retrieveRouteMap(String sourceStop, String destStop) {
		int best_route = -1;
		int source = -1, dest = -1, dist = Integer.MAX_VALUE, least_distance = Integer.MAX_VALUE, source_value = -1,
				dest_value = -1;
		String routeMap = "";
		for (int i = 0; i < number_of_routes; i++) {
			for (int j = 0; j < route[i].routes.size(); j++) {
				if (route[i].routes.get(j).name.equals(sourceStop)){
					source = j;
				}
				if (route[i].routes.get(j).name.equals(destStop)) {
					dest = j;
				}
			}
			if (dist != -1 && source != -1)
				dist = (dest > source) ? (dest - source) : (source - dest);
			System.out.println(dist);
			if (dist < least_distance && dist != Integer.MAX_VALUE) {
				source_value = source;
				dest_value = dest;
				least_distance = dist;
				best_route = i;
			}
		}
		for (int i = source_value; i <= dest_value; i++)
			routeMap = routeMap + ", " + route[best_route].routes.get(i).name;
		String commuterDisplay = "The best route is: " + route[best_route].name + " with Stops: " + routeMap;
		return commuterDisplay;
	}

	public static String retrieveFare(String sourceStop, String destStop) {
		int best_route = -1;
		int source = -1, dest = -1, dist = Integer.MAX_VALUE, least_distance = Integer.MAX_VALUE, source_value = -1,
				dest_value = -1;
		String routeMap = "";
		for (int i = 0; i < number_of_routes; i++) {
			for (int j = 0; j < route[i].routes.size(); j++) {
				if (route[i].routes.get(j).name.equals(sourceStop))
					source = j;
				if (route[i].routes.get(j).name.equals(destStop))
					dest = j;
			}
			if (dist != -1 && source != -1)
				dist = (dest > source) ? (dest - source) : (source - dest);
			System.out.println(dist);
			if (dist < least_distance && dist != Integer.MAX_VALUE) {
				source_value = source;
				dest_value = dest;
				least_distance = dist;
				best_route = i;
			}
		}
		float fare = 2 * least_distance;
		String commuterDisplay = "Trip fare: $" + fare;
		return commuterDisplay;
	}

	public static void registerCommuter(commuters c) {
		commuter_database.add(c);
	}
}
