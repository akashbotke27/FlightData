package com.lnt.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lnt.model.Flight;

@Repository
public class FlightRepo {
	
	public List<Flight> list = new ArrayList<Flight>();
	
	public FlightRepo()
	{
		/*
		 * list.add(new Flight("Asia Travel", "AS-103")); list.add(new
		 * Flight("Europe Travel", "EU-123")); list.add(new Flight("American Travel",
		 * "AM-133"));
		 */
	}
	
	public List<Flight> getAllFlightDetails()
	{
		
		return list;
	}
	
	public Flight getFlight(String Id)
	{
		return list.get(0);
	}

}
