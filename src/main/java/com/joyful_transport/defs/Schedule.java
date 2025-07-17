package src.main.java.com.joyful_transport.defs;

class Schedule {
	public Schedule(Route route, double start,String type) {
		this.route = route;
		this.starttime = start;
		this.type=type;
	}

	Route route;
	double starttime;
	String type;
}
