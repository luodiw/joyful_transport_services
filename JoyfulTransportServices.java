import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.joyful_transport.controls.CentralControlSystem;
import com.joyful_transport.defs.commuters;

public class JoyfulTransportServices {
	public static void main(String[] args) throws InterruptedException {
		new CentralControlSystem();
		commuters c1 = new commuters(50208476);
		commuters c2 = new commuters(50208476);

		// UC1
		c1.registerCommuter();

		// UC2
		c1.retrieveRouteMap("Ellicott Tunnel", "Maynard");
		c1.retrieveFare("Ellicott Tunnel", "Maynard");
		c1.retrieveSchedule("Ellicott Tunnel");
		c1.retrieveRealTimeLocation();
		
		//UC3, UC9
		c1.requestVan("South Campus");
		c2.requestVan("South Campus");
	}
}