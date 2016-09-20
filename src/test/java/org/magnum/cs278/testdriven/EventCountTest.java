package org.magnum.cs278.testdriven;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class EventCountTest {

	private App app = new App();

	@Test
	public void test() {
		
		int count = app.totalEvents();
		List<Event> events = app.getParkSpecialPermits();
		assertNotNull(count);
		assertNotNull(events);
		assertTrue(count == events.size());
	}

}
