package org.magnum.cs278.testdriven;

import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

public class AttendanceFeat_test_group2 {
	
	private App app = new App();
	
	@Test
	public void test() {
		String attendance = "20";
		List<Event> events = app.getEventsAttendedOverLimit_group2(attendance);
		
		for(Event event : events) {
			int eventAttend = Integer.parseInt(event.getAttendance());
			assertTrue(eventAttend > Integer.parseInt(attendance));
		}
	}

}
