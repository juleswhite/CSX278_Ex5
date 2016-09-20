package org.magnum.cs278.testdriven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	public void testIsLeapYear() throws Exception{
	   Event e1 = new Event("n1", "loc1", "0", "may", "1/1/00");
	   Event e2 = new Event("n1", "loc1", "0", "may", "1/1/04");
	   Event e3 = new Event("n1", "loc1", "0", "may", "1/1/01");
	   assertFalse(e1.isLeapYearGroupFour());
	   assertTrue(e2.isLeapYearGroupFour());
	   assertFalse(e3.isLeapYearGroupFour());
	}

}
