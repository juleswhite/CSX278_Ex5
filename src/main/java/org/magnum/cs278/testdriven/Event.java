package org.magnum.cs278.testdriven;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
	
	public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat.forPattern("M/d/yy");

	private final String name;
	private final String location;
	private final String attendance;
	private final String month;
	private final String date;
	
	private final String eventType;

	public Event(@JsonProperty("event") String name,
			@JsonProperty("location") String location,
			@JsonProperty("attendance") String attendance,
			@JsonProperty("month") String month,
			@JsonProperty("date") String date,
			@JsonProperty("event_type") String eventType){
		super();
		this.name = name;
		this.location = location;
		this.attendance = attendance;
		this.month = month;
		this.date = date;
		this.eventType = eventType; 
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getAttendance() {
		return attendance;
	}

	public String getMonth() {
		return month;
	}

	public String getDate() {
		return date;
	}
	
	public String getEventType(){
		return eventType;
	}

	@JsonIgnore
	public DateTime getDateTime() {
		try{
			return DATE_TIME_FORMAT.parseDateTime(getDate());
		}catch(IllegalArgumentException e){
			return DateTime.now();
		}
	}
}
