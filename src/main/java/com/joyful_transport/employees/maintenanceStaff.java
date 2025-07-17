package com.joyful_transport.employees;

import com.joyful_transport.controls.CentralControlSystem;
import com.joyful_transport.vehicles.bus;

public class maintenanceStaff implements employees {
	Integer maintainance_Threshold = 10;

	@Override
	public void checkMaintenanceStatus() {
		Integer number_of_buses = 5;
		bus[] temp = CentralControlSystem.returnBusObject();
		for (int i = 0; i < number_of_buses; i++) {
			if (temp[i].getTripCount() < maintainance_Threshold) {
			} else {
				System.out.println(temp[i].getTripCount() +" Maintenance needed For Bus: " + (i+1));
				CentralControlSystem.removeVehicleFromAvailableList(i);
				SendVehicletoGarage(i);
			}
		}
	}

	@Override
	public void returnVehicleForService(Integer vehicle_number) {
		CentralControlSystem.addVehicleToAvailableList(vehicle_number);
		bus[] temp = CentralControlSystem.returnBusObject();
		temp[vehicle_number].resetTripCount();
	}

	public void SendVehicletoGarage(Integer vehicle_number) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		returnVehicleForService(vehicle_number);
	}
}
