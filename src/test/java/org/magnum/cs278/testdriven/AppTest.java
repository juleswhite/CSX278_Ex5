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
    public void testSmallEvents() throws Exception {
        List<Event> events = app.getSmallEvents();
        
        int maxAttendance = 1000;
        
        for(Event event : events){
            assertTrue(Integer.parseInt(event.getAttendance()) < maxAttendance);
        }
    }
    
	@Test
	public void testGetPrivateEvents() throws Exception {
		List<Event> events = app.getPrivateEvents();
		assertTrue(events.size() > 0);
		for(Event event : events){
			assertEquals(event.getEventType(), "Private");
			assertNotNull(event.getLocation());
			assertNotNull(event.getName());
			assertNotNull(event.getDate());
		}
	}

    @Test
    public void testGetEventsInMonth() throws Exception {
        final String month = "Aug";
        List<Event> events = app.getEventsInMonth(month);
        List<Event> rawEvents = app.getParkSpecialPermits();

        int rawCount = 0;

        for (Event rawEvent : rawEvents) {
            if (rawEvent.getMonth() == month) {
                rawCount++;
            }
        }

        int eventCount = events.size();

        assertTrue(events.size() == rawCount);
        assertTrue(events.size() == eventCount);
        for(Event event : events){
            assertNotNull(event.getMonth());
            assertEquals(month, event.getMonth());

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
		String eventType = "fun";
		Event testEvent = new Event(name, location, attendance, month, date, eventType);
		
		assertEquals(testEvent.toString(), "Name: " + name + ", Location: " + location + ", Attendance: "
				+ attendance + ", Month: " + month + ", Date: " + date+", Event Type: "+eventType);
	}

}
