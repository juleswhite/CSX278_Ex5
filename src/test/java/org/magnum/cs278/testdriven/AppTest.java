package org.magnum.cs278.testdriven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

public class AppTest {

	private App app = new App();
	
	@Test
	public void testGetThreeThingsToDo() throws Exception {
		List<Event> whatToDo = app.getThreeThingsToDo();
		assertEquals(3, whatToDo.size());
		
		DateTime today = DateTime.now();
		
		for(Event thingToDo : whatToDo){
			assertNotNull(thingToDo);
			assertNotNull(thingToDo.getDate());
			
			try{
				DateTime eventDate = Event.DATE_TIME_FORMAT.parseDateTime(thingToDo.getDate());
				assertTrue(eventDate.isAfter(today));
			}catch(IllegalArgumentException arg){
				//The data in data.nashville.gov is..unfortunately...not
				//perfectly clean and we have to ignore the garbage...
			}
		}
	}
	
	@Test
	public void testGetParkSpecialPermits() throws Exception {
		List<Event> events = app.getParkSpecialPermits();
		assertTrue(events.size() > 0);
		for(Event event : events){
			assertNotNull(event);
			assertNotNull(event.getLocation());
			assertNotNull(event.getName());
			assertNotNull(event.getDate());
		}
	}

	
	@Test
	public void testGetRandomEvent() throws Exception {
		Event randomEvent = app.getRandomEvent();
		DateTime today = DateTime.now();
		assertNotNull(randomEvent);
		assertNotNull(randomEvent.getDate());
			
			
		
	}
	
	@Test
	public void testEventToString() throws Exception {
		String name = "Test Event";
		String location = "Test Location";
		String attendance = "150";
		String month = "January";
		String date = "20th";
		Event testEvent = new Event(name, location, attendance, month, date);
		
		assertEquals(testEvent.toString(), "Name: " + name + ", Location: " + location + ", Attendance: "
				+ attendance + ", Month: " + month + ", Date: " + date);
	}

}
