package org.magnum.cs278.testdriven;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class EventCountTest {

	private App app = new App();

	@Test
	public void test() {
		
		int count = app.totalEvents();
		assertNotNull(count);
		List<Event> events;
		try {
			events = app.getParkSpecialPermits();
			assertTrue(count == events.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("error getting events");
		}
	}

}
