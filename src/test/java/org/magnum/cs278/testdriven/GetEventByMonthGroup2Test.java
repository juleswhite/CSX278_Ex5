package org.magnum.cs278.testdriven;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GetEventByMonthGroup2Test {
	
	private App app = new App();

	@Test
	public void testGetEventsByMonthGroup2Test() {
		String mayMonth = "May";
		String aprMonth = "Apr";
		
		List<Event> mayEvents = app.getEventsByMonth_group2(mayMonth);
		
		for (Event e : mayEvents) {
			
			assertTrue(e.getMonth().equals(mayMonth));
			
		}
		
		List<Event> aprEvents = app.getEventsByMonth_group2(aprMonth);
		
		for (Event e : aprEvents) {
			
			assertTrue(e.getMonth().equals(aprMonth));
			
		}
		
	}

}
