package org.magnum.cs278.testdriven;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A simple application that pull special event permits for the Nashville parks
 * and displays 3 of them. The way it displays the permits isn't very helpful.
 * 
 * @author jules
 *
 */
public class App {

	// This is a JSON feed of the park special permits
	private static final String PARK_SPECIAL_PERMITS = "http://data.nashville.gov/resource/vygj-v677.json";

	// An ObjectMapper turns JSON into Java Objects (e.g., Event instances)
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final JavaType eventListType = objectMapper.getTypeFactory()
			.constructCollectionType(List.class, Event.class);

	/**
	 * The entry point to Java applications is a "main" method with the exact
	 * signature shown below.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		App app = new App();
		//List<Event> evts = app.getThreeThingsToDo();
		List<Event> hi = app.getSmallEvents();
		for (Event e : hi) {
			System.out.println(e);
		}
		List<Event> evts2 = app.getEventsAttendedOverLimit_group2(20);
				for (Event e : evts) {
					System.out.println(e);
				}	
		
	}

	// Download the list of special event park permits and select
	// the first three permits that are in the future
	public List<Event> getThreeThingsToDo() throws Exception {
		List<Event> toDo = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();

		DateTime now = DateTime.now();
		for (Event evt : evts) {
			if (evt.getDateTime().isAfter(now)) {
				toDo.add(evt);
				if (toDo.size() >= 3) {
					break;
				}
			}
		}
		
		// If we were using Java 8, we could replace this method with this
		// one-liner:
		//
		//return evts.stream()
		//			.filter(e -> e.getDateTime().isAfter(DateTime.now()))
		//          .limit(3)
		//			.collect(Collectors.toList());

		return toDo;
	}
	
	public List<Event> getEventsInMonth(String month) throws Exception{
		List<Event> inMonth = new ArrayList<>();
		List<Event> evts = getParkSpecialPermits();
		
		for (Event existingEvent: evts) {
			if (existingEvent.getDate().trim().equalsIgnoreCase(month)) {
				inMonth.add(existingEvent);
			}
		}
		
		return inMonth;
	}

	public Event getRandomEvent() throws Exception{
		List<Event> evts = getParkSpecialPermits();
		int index = (int) Math.ceil(Math.random()*evts.size());
		Event randomEvt = evts.get(index);
		return randomEvt;		
	}

	// Download a list of all special event park permits for Nashville.
	public List<Event> getParkSpecialPermits() throws Exception {
		
		// Read the JSON from the provided URL and turn it into
		// a list of Event object instances. The ObjectMapper automatically
		// sets the member variable values on the instances by mapping
		// JSON fields to the corresponding "setX" methods in the target
		// class. For example, "location" is set on the target object
		// by calling "setLocation( value )" with the value of the JSON
		// field.
		return objectMapper.readValue(new URL(
				PARK_SPECIAL_PERMITS),
				eventListType
				);
	}
	
	public List<Event> getEventsByMonth_group2(String month) throws Exception {
		List<Event> listByMonth = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();
		for (Event evt : evts){
			if (evt.getMonth().equals(month)){
				listByMonth.add(evt);
			}
			
		}
		return listByMonth;
}

	//filter events by name Group2 Neil Advani
	public List<Event> getEventByName_Group2(String eventName) throws Exception {
		List<Event> toReturn = new ArrayList<Event>();
		List<Event> events = getParkSpecialPermits();
		
		for (Event evt : events) {
			if (evt.getName().toLowerCase().equals(eventName.toLowerCase())) {
				toReturn.add(evt);
			}
		}
		return toReturn;
	}
	
	public List<Event> getEventsAttendedOverLimit_group2(int limit) throws Exception {
		List<Event> attended = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();

		DateTime now = DateTime.now();
		for (Event evt : evts) {
			if (Integer.parseInt(evt.getAttendance()) > limit) {
				attended.add(evt);
			}
		}
		return attended;
	}
	
	public List<Event> getSmallEvents() throws Exception {
		List<Event> toDo = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();
		
		int maxAttendance = 1000;
		for (Event evt : evts) {
			if  (evt.getAttendance() == null){
				continue;
			} else if (Integer.parseInt(evt.getAttendance()) < maxAttendance) {
				toDo.add(evt);
			}		
		}
		return toDo;
	}
	
	public List<Event> getPrivateEvents() throws Exception{
		List<Event> eventList = getParkSpecialPermits();
		List<Event> privateEventList = new ArrayList<Event>();
		for(Event event: eventList){
			if(event.getEventType().equals("Private")){
				privateEventList.add(event);
			}
		}
		return privateEventList;
	}
	
	public int totalEvents() {
		List<Event> events;
		try {
			events = getParkSpecialPermits();
			return events.size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
}
