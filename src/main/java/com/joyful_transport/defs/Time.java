package com.joyful_transport.defs;

import com.joyful_transport.controls.CentralControlSystem;

public class Time implements Runnable {
	static float time = 0;
	float hour = 0;

	public float getHour() {
		hour = time / 5;
		return hour;
	}

	public Time() {
		Thread timeThread = new Thread();
		timeThread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (time == 120) {
				CentralControlSystem.displayFareCollected();
				time = 0;
			} else {
				time = time + 1;
			}
		}
	}
}
